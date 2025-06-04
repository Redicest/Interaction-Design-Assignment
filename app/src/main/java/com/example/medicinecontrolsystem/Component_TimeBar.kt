package com.example.medicinecontrolsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimeBar(modifier:Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = stringResource(R.string.time_morning),
                fontWeight = FontWeight.W400,
                fontSize = 48.sp
            )
            Text(
                text = stringResource(R.string.time_noon),
                fontWeight = FontWeight.W400,
                fontSize = 48.sp
            )
            Text(
                text = stringResource(R.string.time_evening),
                fontWeight = FontWeight.W400,
                fontSize = 48.sp
            )
            Text(
                text = stringResource(R.string.time_beforeSleep),
                fontWeight = FontWeight.W400,
                fontSize = 48.sp
            )
        }
    }
}

@Composable
fun InteractiveBarBelowText(){

}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun TimeBarPreview(){
    TimeBar()
}