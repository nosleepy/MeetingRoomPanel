package com.gs.panel.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.PanelApplication
import com.gs.panel.api.Api
import com.gs.panel.api.safeApiCall
import com.gs.panel.entity.ScheduleItem
import com.gs.panel.util.ToastUtil
import kotlinx.coroutines.launch
import java.util.Collections

class ConfListViewModel: ViewModel() {
    var scheduleMap = mutableStateMapOf<String, MutableList<ScheduleItem>>()

    init {
        viewModelScope.launch {
            val gscConfReservationRes = safeApiCall {
                Api.get().listGscPhyConfReservation(PanelApplication.confId, PanelApplication.cookie)
            }
            if (gscConfReservationRes.isSuccess()) {
                Collections.sort(gscConfReservationRes.response!!.conference) {
                    o1, o2 -> o1.configStartTime.compareTo(o2.configStartTime)
                }
                gscConfReservationRes.response!!.conference.forEach {
                    val configStartDate = it.configStartTime.split(" ")[0]
                    it.configStartTime = it.configStartTime.split(" ")[1]
                    it.configEndTime = it.configEndTime.split(" ")[1]
                    it.confReservationStatus = when (it.confReservationStatus) {
                        "about_to_begin" -> "即将开始"
                        "inuse" -> "进行中"
                        "not_begin" -> "未开始"
                        else -> ""
                    }
                    if (scheduleMap.containsKey(configStartDate)) {
                        val scheduleList = scheduleMap[configStartDate]!!.apply { add(it) }
                        scheduleMap[configStartDate] = scheduleList
                    } else {
                        scheduleMap[configStartDate] = mutableListOf(it)
                    }
                }
                scheduleMap.forEach {
                    it.value.sortWith { o1, o2 -> o1.configStartTime.compareTo(o2.configStartTime) }
                }
            } else {
                ToastUtil.show(gscConfReservationRes.getErrorMsg())
            }
        }
    }
}