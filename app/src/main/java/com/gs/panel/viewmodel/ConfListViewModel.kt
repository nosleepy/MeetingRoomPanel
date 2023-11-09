package com.gs.panel.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.CustomApplication
import com.gs.panel.api.Api
import com.gs.panel.entity.ScheduleItem
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.launch

class ConfListViewModel: ViewModel() {
    private var scheduleMap = mutableMapOf<String, MutableList<ScheduleItem>>()

    init {
        viewModelScope.launch {
            val gscConfRes = Api.get().listGscPhysicalConfTimeListByDay(
                "${TimeUtil.getTodayDate()} 00:00",
                "${TimeUtil.getTodayDate()} 23:59",
                CustomApplication.cookie
            )
            val reservationRes = Api.get().listGscPhyConfReservation(
                gscConfRes.response!!.conference[0].confId,
                CustomApplication.cookie
            )
            Log.d("wlzhou", "reservationRes = $reservationRes")
            reservationRes.response!!.conference.forEach {
                val configStartTime = it.configStartTime.split(" ")[0]
                if (scheduleMap.containsKey(configStartTime)) {
                    val scheduleList = scheduleMap[configStartTime]!!.apply { add(it) }
                    scheduleMap[configStartTime] = scheduleList
                } else {
                    scheduleMap[configStartTime] = mutableListOf<ScheduleItem>(it)
                }
            }
            Log.d("wlzhou", "scheduleMap = $scheduleMap")
        }
    }
}