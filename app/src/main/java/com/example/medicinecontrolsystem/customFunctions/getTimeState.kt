package com.example.medicinecontrolsystem.customFunctions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimeBarViewModel: ViewModel(){
    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex:StateFlow<Int> = _selectedIndex.asStateFlow()

    // 使用可变状态作为替代方案
    private var _altSelectedIndex by mutableStateOf(0)
    val altSelectedIndex get() = _altSelectedIndex

    // 设置选中的时间段
    fun setSelectedIndex(index: Int) {
        if (index in 0..3) {
            // 使用 StateFlow
            viewModelScope.launch {
                _selectedIndex.value = index
            }

            // 使用可变状态
            _altSelectedIndex = index
        }
    }

    // 获取当前选中的时间段名称
    fun getSelectedPeriodName(): String {
        return when (selectedIndex.value) {
            0 -> "早晨"
            1 -> "中午"
            2 -> "晚上"
            3 -> "睡前"
            else -> "未知"
        }
    }

    // 重置状态
    fun resetSelection() {
        setSelectedIndex(0)
    }
}