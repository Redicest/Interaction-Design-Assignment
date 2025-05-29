package com.example.medicinecontrolsystem

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.medicinecontrolsystem.ui.theme.MedicineControlSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicineControlSystemTheme {
                MainPageApp()
            }
        }
    }
}

// 页面整体布局
@Composable
fun MainPageApp(modifier: Modifier = Modifier){
    val context = LocalContext.current
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
        ImageWithText(
            image_id = R.drawable.icon_camera,
            text_id = R.string.mainpage_takingPhotos,
            onclick = {
                val intent = Intent(context, IdentityActivity::class.java)
                context.startActivity(intent)
            }
            )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        ImageWithText(
            image_id = R.drawable.icon_bell,
            text_id = R.string.mainpage_medicineReminder,
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            ImageWithText(
                image_id = R.drawable.icon_record,
                text_id = R.string.mainpage_updateRecord,
                )
            Spacer(modifier = Modifier.width(25.dp))
            ImageWithText(
                image_id = R.drawable.icon_identity,
                text_id = R.string.mainpage_individualCenter,
            )
        }
    }
}

@Composable
fun ImageWithText(
    @DrawableRes image_id:Int,
    @StringRes text_id:Int,
    onclick:() -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.clickable{ onclick() },
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Image(
            painter = painterResource(image_id),
            contentDescription = null,
        )
        Text(
            text = stringResource(text_id),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MedicineControlSystemTheme {
        MainPageApp()
    }
}