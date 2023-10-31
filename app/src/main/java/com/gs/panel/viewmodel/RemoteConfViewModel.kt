package com.gs.panel.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.gs.panel.CustomApplication
import com.gs.panel.api.Api
import com.gs.panel.entity.FacilityItem
import com.gs.panel.state.LocalConfState
import com.gs.panel.util.FileUtil
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class RemoteConfState {
    IDLE,
    READY_FLAG,
    READY,
    RUN,
    DISABLE
}

class RemoteConfViewModel : ViewModel() {
    var confState by mutableStateOf(RemoteConfState.IDLE)
    var showErrorDialog by mutableStateOf(false)
    var showMoreDeviceDialog by mutableStateOf(false)
    var showDelayConfDialog by mutableStateOf(false)
    var showStartConfDialog by mutableStateOf(false)
    var showStopConfDialog by mutableStateOf(false)
    var showStartConfSuccessDialog by mutableStateOf(false)
    var showDelayConfSuccessDialog by mutableStateOf(false)

    private val mainScope = MainScope()
    private var timeJob: Job

    private var index = 0
    private val confStateList = listOf<RemoteConfState>(
        RemoteConfState.IDLE,
        RemoteConfState.READY_FLAG,
        RemoteConfState.READY,
        RemoteConfState.RUN,
        RemoteConfState.DISABLE,
    )

    var facilityList by mutableStateOf(listOf<FacilityItem>())

    init {
        timeJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
                delay(10000)
                index = (index + 1) % 5
                confState = confStateList[index]
            }
        }
        viewModelScope.launch {
            val res = Api.get().getGscAccessToken(FileUtil.getUsername(), FileUtil.getPassword())
            if (res.isSuccess()) {
                val loginRes = Api.get().login(res.response!!.extenAccount, res.response!!.token)
                Log.d("MeetingRoomPanel", "loginRes = $loginRes")
                CustomApplication.cookie = loginRes.response!!.cookie
                val gscConfRes = Api.get().listGscPhysicalConfTimeListByDay("2023-10-31 00:00", "2023-10-31 23:59", CustomApplication.cookie)
                Log.d("MeetingRoomPanel", "gscConfRes = $gscConfRes")
                facilityList = mutableListOf<FacilityItem>().apply {
                    add(FacilityItem(-1, "", "${gscConfRes.response!!.conference[0].memberCapacity}人", ""))
                    addAll(gscConfRes.response!!.conference[0].facilities)
                    add(FacilityItem(0, "", "More", ""))
                }
            }
        }
    }
}