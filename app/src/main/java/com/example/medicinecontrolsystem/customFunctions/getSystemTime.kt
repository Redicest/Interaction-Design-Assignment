
package com.example.medicinecontrolsystem.customFunctions

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

open class TimeViewModel : ViewModel() {
    // 使用 State 而不是 MutableState 对外暴露
    private val _hour = mutableStateOf(0)
    val hour: State<Int> get() = _hour

    private val _minute = mutableStateOf(0)
    val minute: State<Int> get() = _minute

    private val _year = mutableStateOf(0)
    val year: State<Int> get() = _year

    private val _month = mutableStateOf(0)
    val month: State<Int> get() = _month

    private val _day = mutableStateOf(0)
    val day: State<Int> get() = _day

    private val _weekDay = mutableStateOf(0)
    val weekDay: State<Int> get() = _weekDay

    private val _formattedDate = mutableStateOf("")
    val formattedDate: State<String> get() = _formattedDate

    private val _formattedTime = mutableStateOf("")
    open val formattedTime: State<String> get() = _formattedTime

    private val _formattedWeekDay = mutableStateOf("")
    val formattedWeekDay: State<String> get() = _formattedWeekDay

    private val _formattedYear = mutableStateOf("")
    val formattedYear: State<String> get() = _formattedYear

    private val _timePhrase = mutableStateOf("")
    val timePhrase: State<String> get() = _timePhrase

    init {
        startTimeUpdater()
    }

    private fun startTimeUpdater() {
        viewModelScope.launch {
            while (true) {
                updateTime()
                delay(1000)
            }
        }
    }

    private fun updateTime() {
        val calendar = Calendar.getInstance()
        _hour.value = calendar.get(Calendar.HOUR_OF_DAY)
        _minute.value = calendar.get(Calendar.MINUTE)
        _formattedTime.value = String.format("%02d:%02d", _hour.value, _minute.value)

        _year.value = calendar.get(Calendar.YEAR)
        _month.value = calendar.get(Calendar.MONTH) + 1
        _day.value = calendar.get(Calendar.DAY_OF_MONTH)
        _formattedDate.value = "${_month.value}月${_day.value}日"
        _formattedYear.value = "${_year.value}年"

        _weekDay.value = calendar.get(Calendar.DAY_OF_WEEK)
        _formattedWeekDay.value = getWeekDayName(_weekDay.value)

        _timePhrase.value = getTimePhrase(_hour.value)
    }

    private fun getWeekDayName(weekDay: Int): String {
        return when (weekDay) {
            Calendar.SUNDAY -> "星期天"
            Calendar.MONDAY -> "星期一"
            Calendar.TUESDAY -> "星期二"
            Calendar.WEDNESDAY -> "星期三"
            Calendar.THURSDAY -> "星期四"
            Calendar.FRIDAY -> "星期五"
            Calendar.SATURDAY -> "星期六"
            else -> "时间错误"
        }
    }

    private fun getTimePhrase(hour: Int): String {
        return if (hour in 0..11) "上午" else "下午"
    }

    // 添加一个获取当前日期的方法
    open fun getCurrentDate(): Triple<Int, Int, Int> {
        return Triple(year.value, month.value, day.value)
    }
}