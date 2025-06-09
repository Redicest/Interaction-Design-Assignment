package com.example.medicinecontrolsystem.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.medicinecontrolsystem.R

data class data_Patient(
    val id:Int,
    @DrawableRes val imageResourceId:Int,
    @StringRes val patientName:Int,
    @StringRes val patientBedNumber:Int,
)

val patients = listOf(
    data_Patient(1, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(2, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(3, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(4, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(5, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(6, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(7, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(8, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
    data_Patient(9, R.drawable.oip_c,R.string.patient_1_name,R.string.patient_1_bednumber),
)
