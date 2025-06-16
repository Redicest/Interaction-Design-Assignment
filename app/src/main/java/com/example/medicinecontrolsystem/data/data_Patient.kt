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
    data_Patient(1, R.drawable.patient1,R.string.patient_1_name,R.string.patient_1_bednumber,1, "8:00", "2025.6.18"),
    data_Patient(2, R.drawable.patient2,R.string.patient_2_name,R.string.patient_2_bednumber,2, "9:30", "2025.6.18"),
    data_Patient(3, R.drawable.patient3,R.string.patient_3_name,R.string.patient_3_bednumber,3, "12:00", "2025.6.18"),
    data_Patient(4, R.drawable.patient1,R.string.patient_4_name,R.string.patient_4_bednumber,4, "null", "null"), // 无提醒
    data_Patient(5, R.drawable.patient2,R.string.patient_5_name,R.string.patient_5_bednumber,5, "8:00", "2025.6.19"),
    data_Patient(6, R.drawable.patient3,R.string.patient_6_name,R.string.patient_6_bednumber,6, "8:00", "2025.6.19"),
    data_Patient(7, R.drawable.patient1,R.string.patient_7_name,R.string.patient_7_bednumber,7, "12:30", "2025.6.20"),
    data_Patient(8, R.drawable.patient2,R.string.patient_8_name,R.string.patient_8_bednumber,8, "15:00", "2025.6.20"),
    data_Patient(9, R.drawable.patient3,R.string.patient_9_name,R.string.patient_9_bednumber,9, "17:00", "2025.6.20"),
)