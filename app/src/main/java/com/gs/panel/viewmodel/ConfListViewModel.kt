package com.gs.panel.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.CustomApplication
import com.gs.panel.api.Api
import com.gs.panel.entity.ScheduleItem
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.launch
import java.util.Collections

@SuppressLint("MutableCollectionMutableState")
class ConfListViewModel: ViewModel() {
    var scheduleMap by mutableStateOf(mutableMapOf<String, MutableList<ScheduleItem>>())

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
            Collections.sort(reservationRes.response!!.conference) {
                o1, o2 -> o1.configStartTime.compareTo(o2.configStartTime)
            }
            reservationRes.response!!.conference.forEach {
                val configStartDate = it.configStartTime.split(" ")[0]
                it.configStartTime = it.configStartTime.split(" ")[1]
                it.configEndTime = it.configEndTime.split(" ")[1]
                it.confReservationStatus = when (it.confReservationStatus) {
                    "about_to_begin" -> "即将开始"
                    "issue" -> "进行中"
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
//            scheduleMap.forEach {
//                it.value.sortWith { o1, o2 -> o1.configStartTime.compareTo(o2.configStartTime) }
//            }
        }
    }
}