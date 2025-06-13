package com.example.medicinecontrolsystem.ComponentMainPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.R
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

@Composable
fun CenterInformation(
    modifier:Modifier = Modifier,
    centerInformationViewModel: TimeViewModel
    ){
    val timePhrase by centerInformationViewModel.timePhrase
    val timeFormatted by centerInformationViewModel.formattedTime
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Text(
            text = stringResource(R.string.currentTasks),
            fontSize = 14.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            text = timePhrase + timeFormatted,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Composable
fun CenterInformationP(
    modifier:Modifier = Modifier,
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = stringResource(R.string.currentTasks),
            fontSize = 14.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            text = "下午 3:20",
            fontSize = 14.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
fun CenterInformationRowP(){
    CenterInformationP()
}