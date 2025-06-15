package com.example.medicinecontrolsystem.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.medicinecontrolsystem.R

data class data_Patient(
    val id:Int,
    @DrawableRes val imageResourceId:Int,
    @StringRes val patientName:Int,
    @StringRes val patientBedNumber:Int,
    val medicineBoxId:Int,
    val reminderTime:String,
    val reminderDate: String
)

val patients = listOf(
    data_Patient(1, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,1, "8:00", "2025.6.1"),
    data_Patient(2, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,2, "12:30", "2025.6.1"),
    data_Patient(3, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,3, "18:00", "2025.6.1"),
    data_Patient(4, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,4, "null", "null"), // 无提醒
    data_Patient(5, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,5, "22:00", "2025.6.1"),
    data_Patient(6, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,6, "8:00", "2025.6.2"),
    data_Patient(7, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,7, "12:30", "2025.6.2"),
    data_Patient(8, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,8, "18:00", "2025.6.2"),
    data_Patient(9, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber,9, "22:00", "2025.6.2"),
)