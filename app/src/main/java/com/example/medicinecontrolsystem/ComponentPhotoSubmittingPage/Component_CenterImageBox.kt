package com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import android.R.attr.title
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.medicinecontrolsystem.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun CenterImagePart(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD9F0FF)),
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.zj03hicr),
                    contentDescription = "taken photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp, 200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.size(width = 150.dp, height = 40.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color(0xFF03A9F4)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(
                                R.string.adding_photo
                            ),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W800,
                            color = Color.White
                        )
                    }
                }

                Card(
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.size(width = 150.dp, height = 40.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color(0xFFFFFFFF)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(
                                R.string.scanning_again
                            ),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.padding(horizontal = 40.dp)
            ){
                AdvancedInputField(
                    placeholder = "备注",
                    leadingIcon = {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "备注图标",
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun AdvancedInputField(
    initialValue: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = 100,
    placeholder: String = "请输入内容...",
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    onDismiss:()->Unit = {},
    onConfirm:(String)->Unit = {},
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf(initialValue) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .background(Color(0xFFD9F0FF)) // 整个组件背景色（蓝色）
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                if (it.length <= maxLength) {
                    inputText = it
                    isError = false
                    onValueChange(it)
                } else {
                    isError = true
                    errorMessage = "超过最大长度 ($maxLength 字符)"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .heightIn(min = 200.dp)
                .background(color = Color(0xFFD9F0FF), RoundedCornerShape(4.dp)), // 文本框内部背景（白色）
            singleLine = keyboardType != KeyboardType.Text,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError,
            colors = TextFieldDefaults.colors( // 关键修改：设置文本区域背景色
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium, // 保持默认形状
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    leadingIcon?.invoke()
                    if (leadingIcon != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = placeholder,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            placeholder = {
                Text(
                    text = "在此输入$placeholder...",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            },
            supportingText = {
                // 移除Column背景设置，直接显示文本
                if (isError) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Text(
                        text = "${inputText.length}/$maxLength",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card(
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFFFFD700)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    TextButton(
                        onClick = {onConfirm(inputText)},
                        enabled = inputText.isNotBlank()
                    ) {
                        Text(
                            text = "确定",
                            color = Color(0xFFFFFFFF),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W800,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Card(
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFFFFFFFF)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "取消",
                            color = Color(0xFFFFD700),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W600,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CenterImagePartPreview(){
    CenterImagePart()
}