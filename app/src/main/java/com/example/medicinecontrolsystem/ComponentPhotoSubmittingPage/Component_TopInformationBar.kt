package com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinecontrolsystem.R

@Composable
fun TopInformationBar(modifier:Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = stringResource(R.string.sending_medicine_record),
            fontWeight = FontWeight.W800,
            fontSize = 48.sp
        )
        Row(){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier.size(70.dp).align(Alignment.CenterVertically),
                tint = Color(0xFF03A9F4)
            )
            Text(
                text = stringResource(R.string.filter_record),
                fontWeight = FontWeight.W400,
                fontSize = 36.sp,
                color = Color(0xFF03A9F4),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

    }
}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun TopInformationBarPreview(){
    TopInformationBar()
}