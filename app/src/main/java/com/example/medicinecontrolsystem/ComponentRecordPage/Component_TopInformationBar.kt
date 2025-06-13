package com.example.medicinecontrolsystem.ComponentRecordPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.medicinecontrolsystem.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopInformationBar(
    modifier: Modifier = Modifier,
    baseUnit: Dp // 添加基础单位参数
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 1.5f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.sending_medicine_record),
            fontWeight = FontWeight.W800,
            fontSize = (baseUnit.value * 1.2).sp // 相对字体大小
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier.size(baseUnit * 1.75f), // 相对图标大小
                tint = Color(0xFF03A9F4)
            )
            Spacer(modifier = Modifier.width(baseUnit * 0.3f))
            Text(
                text = stringResource(R.string.filter_record),
                fontWeight = FontWeight.W400,
                fontSize = (baseUnit.value).sp, // 相对字体大小
                color = Color(0xFF03A9F4)
            )
        }
    }
}
@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun TopInformationBarPreview(){

}
