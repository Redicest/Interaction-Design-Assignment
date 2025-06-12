package com.example.medicinecontrolsystem.customFunctions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
*函数接口：
* updateMedicineState(patientId: Int, taken: Boolean) 更新病人是否服药状态
* getMedicineState(patientId: Int): Boolean 返回病人是否服药
*
*/

class MedicineTakingStateViewModel @Inject constructor() : ViewModel() {
    // 使用病人ID作为键存储状态
    private val _medicineStates = mutableMapOf<Int, Boolean>()
    private val _statesFlow = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val statesFlow: StateFlow<Map<Int, Boolean>> = _statesFlow.asStateFlow()

    // 更新特定病人的状态
    fun updateMedicineState(patientId: Int, taken: Boolean) {
        viewModelScope.launch {
            _medicineStates[patientId] = taken
            _statesFlow.value = _medicineStates.toMap() // 创建新映射触发更新
        }
    }

    // 获取特定病人的状态
    fun getMedicineState(patientId: Int): Boolean {
        return _medicineStates[patientId] ?: false
    }
}