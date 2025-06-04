package com.example.medicinecontrolsystem.customFunctions

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

class TimeViewModel: ViewModel() {
    val hour = mutableStateOf(0)
    val minute = mutableStateOf(0)

    val year = mutableStateOf(0)
    val month = mutableStateOf(0)
    val day = mutableStateOf(0)

    val weekDay = mutableStateOf(0)

    val formattedData = mutableStateOf("")
    val formattedTime = mutableStateOf("")
    val formattedWeekDay = mutableStateOf("")

    init {
        startTimeUpdater()
    }

    private fun startTimeUpdater(){
        viewModelScope.launch{
            while(true){
                updateTime()
                delay(1000)
            }
        }
    }

    private fun updateTime() {
        val calendar = Calendar.getInstance()
        hour.value = calendar.get(Calendar.HOUR_OF_DAY)
        minute.value = calendar.get(Calendar.MINUTE)

        // 使用更简洁的时间格式化方式
        formattedTime.value = String.format("%02d:%02d", hour.value, minute.value)

        year.value = calendar.get(Calendar.YEAR)
        month.value = calendar.get(Calendar.MONTH) + 1 // 月份从0开始，需要+1
        day.value = calendar.get(Calendar.DAY_OF_MONTH)
        formattedData.value = "${month.value}月${day.value}日"

        weekDay.value = calendar.get(Calendar.DAY_OF_WEEK)
        formattedWeekDay.value = getWeekDayName(weekDay.value)
    }

    private fun getWeekDayName(weekDay:Int): String{
        return when(weekDay){
            Calendar.SUNDAY->"星期天"
            Calendar.MONDAY->"星期一"
            Calendar.TUESDAY->"星期二"
            Calendar.WEDNESDAY->"星期三"
            Calendar.THURSDAY->"星期四"
            Calendar.FRIDAY->"星期五"
            Calendar.SATURDAY->"星期六"
            else->"时间错误"
        }
    }
}