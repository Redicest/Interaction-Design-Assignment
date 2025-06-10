package com.example.medicinecontrolsystem.ComponentRecordPage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.medicinecontrolsystem.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.data.patients
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel
import com.example.medicinecontrolsystem.data.data_Patient

@Composable
fun PatientRecordList(
    modifier:Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    systemTimeViewModel: TimeViewModel
    ){
    LazyColumn{
        items(patients) {
            PatientRecordItem(
                patient = it,
                viewModel = viewModel,
                systemTimeViewModel = systemTimeViewModel
            )
        }
    }
}

@Composable
fun PatientRecordItem(
    patient: data_Patient,
    modifier: Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    systemTimeViewModel: TimeViewModel
){
    // 观察该病人的状态变化
    val medicineState by viewModel.statesFlow.collectAsState()
    val isTaken:Boolean = medicineState[patient.id] ?: false

    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedDate
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, bottom = 20.dp)
            .height(600.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.zj03hicr),
                contentDescription = "病人记录背景",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(bottom = 30.dp)
            ){
                Row(
                    verticalAlignment = Alignment.Bottom
                ){
                    PatientImage(
                        patient.imageResourceId,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    PatientText(
                        patient.patientName,
                        patient.patientBedNumber,
                        formattedDate,
                        formattedTime,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = 50.dp)
            ){
                signIfmedicineTaken(
                    isTaken = isTaken,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun PatientImage(
    @DrawableRes patientImage:Int,
    modifier: Modifier=Modifier
){
    Image(
        modifier = modifier.size(170.dp),
        painter = painterResource(patientImage),
        contentDescription = null
    )
}

@Composable
fun PatientText(
    @StringRes patientName:Int,
    @StringRes patientBedNumber:Int,
    dateString: String,
    timeString: String,
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Row(){
            Text(
                text = stringResource(patientName),
                fontSize = 48.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = stringResource(patientBedNumber),
                fontSize = 48.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        }
        Row(){
            Text(
                text = dateString + timeString,
                fontSize = 38.sp,
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        }
    }
}

@Composable
fun signIfmedicineTaken(isTaken:Boolean, modifier: Modifier = Modifier){
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier.size(width = 150.dp, height = 70.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isTaken) Color(0xFFFFD700)
                    else Color(0xFF989898)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(
                    if (isTaken) R.string.have_taking_medicine
                    else R.string.not_taking_medicine
                ),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun PatientRecordListPreview(){
    PatientRecordList()
}