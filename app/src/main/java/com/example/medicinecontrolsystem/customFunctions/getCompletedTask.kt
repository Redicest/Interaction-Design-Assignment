package com.example.medicinecontrolsystem.customFunctions

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CompletedTaskViewModel : ViewModel(){
    private val _completedTasks = MutableStateFlow(2)
    private val _totalTasks = MutableStateFlow(7)

    val completedTasks: StateFlow<Int> = _completedTasks
    val totalTasks: StateFlow<Int> = _totalTasks

    fun completedTask(){
        viewModelScope.launch {
            if(_completedTasks.value < _totalTasks.value){
                _completedTasks.value += 1
            }
        }
    }

    fun resetTask(){
        viewModelScope.launch {
            _completedTasks.value = 0
        }
    }

    fun setTotalTasks(total:Int){
        viewModelScope.launch {
            _totalTasks.value = total
            if(_completedTasks.value > total){
                _completedTasks.value = total
            }
        }
    }

    fun getTaskProgress(): Pair<Int, Int>{
        return Pair(_completedTasks.value, _totalTasks.value)
    }

    fun getProgressPercentage():Float{
        return if (_totalTasks.value > 0){
            _completedTasks.value.toFloat() / _totalTasks.value.toFloat()
        } else {
            0f
        }
    }
}