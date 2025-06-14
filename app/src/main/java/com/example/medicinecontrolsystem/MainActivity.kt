package com.example.medicinecontrolsystem


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medicinecontrolsystem.ComponentMainPage.CenterInformation
import com.example.medicinecontrolsystem.ComponentMainPage.PatientInformationList
import com.example.medicinecontrolsystem.ComponentMainPage.TimeBar
import com.example.medicinecontrolsystem.ComponentMainPage.TopInformationCard
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.CenterImagePart
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.TopInformationBarP
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.TopInformationBarPageSubmitting
import com.example.medicinecontrolsystem.ComponentRecordPage.CenterDateBarList
import com.example.medicinecontrolsystem.ComponentRecordPage.PatientRecordList
import com.example.medicinecontrolsystem.ComponentRecordPage.TopInformationBar
import com.example.medicinecontrolsystem.customFunctions.CompletedTaskViewModel
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel
import com.example.medicinecontrolsystem.customFunctions.TimeBarViewModel
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.data.patients
import com.example.medicinecontrolsystem.ui.theme.MedicineControlSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicineControlSystemTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        // 隐藏底部导航栏当在照片提交页面时
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        if (currentRoute != "photo_submit") {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(
                            route = "home",
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300))
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            }
                        ) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = "record",
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            }
                        ) {
                            RecordScreen()
                        }
                        composable(
                            route = "profile",
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300))
                            }
                        ){
                            ProfileFragment()
                        }
                        composable(
                            route = "photo_submit/{patientId}",
                            enterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                            }
                        ){ backStackEntry->
                            val patientId = backStackEntry.arguments?.getString("patientId")?.toIntOrNull()
                            PhotoSubmittingScreen(patientId = patientId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    // 获取 ViewModel 实例
    val timeViewModel: TimeViewModel = viewModel()
    val timeBarViewModel: TimeBarViewModel = viewModel()
    val completedTaskViewModel: CompletedTaskViewModel = viewModel()
    val medicineTakingStateViewModel: MedicineTakingStateViewModel = viewModel()

    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFCF7),
                        Color(0xFFE6F1FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = baseUnit * 1.5f)
        ) {
            TopInformationCard(
                gradinetBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFD9F0FF),
                        Color(0xFFF2F7FB)
                    )
                ),
                systemTimeViewModel = timeViewModel,
                completedTaskViewModel = completedTaskViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 20f)
            )
            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
            TimeBar(
                timeBarViewModel = timeBarViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 3.3f)
            )
            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
            CenterInformation(
                centerInformationViewModel = timeViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 3f)
            )
            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
            PatientInformationList(
                navController = navController,
                medicineTakingStateViewModel = medicineTakingStateViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                baseUnit = baseUnit
            )
        }
    }
}

@Composable
fun RecordScreen(){
    // 获取 ViewModel 实例
    val timeViewModel: TimeViewModel = viewModel()
    val medicineTakingStateViewModel: MedicineTakingStateViewModel = viewModel()

    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFCF7),
                        Color(0xFFE6F1FF)
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = baseUnit * 1.5f)
        ) {
            Spacer(modifier = Modifier.height(baseUnit * 3f))
            TopInformationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 4f),
                baseUnit = baseUnit
            )
            Spacer(modifier = Modifier.height(baseUnit))
            CenterDateBarList(
                timeViewModel = timeViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 8f),
                baseUnit = baseUnit
            )
            Spacer(modifier = Modifier.height(baseUnit))
            PatientRecordList(
                viewModel = medicineTakingStateViewModel,
                systemTimeViewModel = timeViewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                baseUnit = baseUnit // 传递基础单位
            )
        }
    }
}

@Composable
fun PhotoSubmittingScreen(patientId: Int?) {
    // 获取屏幕尺寸并计算基础单位
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    if (patientId == null) {
        ErrorScreen("无效的患者ID")
        return
    }
    // 根据ID查找病人信息
    val patient = remember(patientId) {
        patients.find { it.id == patientId }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // 顶部区域
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)   // 使用 weight 函数分配空间
                .align(alignment = Alignment.CenterHorizontally)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.height(baseUnit * 4))
                if (patient != null) {
                    TopInformationBarPageSubmitting(
                        patient = patient,
                        baseUnit = baseUnit
                    )
                } else {
                    Text("未选择")
                }
            }
        }

        // 底部区域
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f) // 使用 weight 函数分配空间
        ) {
            CenterImagePart(
                baseUnit = baseUnit
            )
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = Color.Red)
    }
}

//@Preview(widthDp = 1080, heightDp = 2160)
//@Composable
//fun HomeScreenPreview() {
//    // 创建模拟 ViewModel
//    val mockTimeViewModel = TimeViewModel()
//    val mockTimeBarViewModel = TimeBarViewModel()
//    val mockCompletedTaskViewModel = CompletedTaskViewModel()
//    val mockMedicineStateViewModel = MedicineTakingStateViewModel()
//
//    MedicineControlSystemTheme {
//        Scaffold(
//            bottomBar = { BottomNavBar(navController = rememberNavController()) }
//        ) { innerPadding ->
//            Box(Modifier.padding(innerPadding)) {
//                HomeScreen(
//                    navController = rememberNavController(),
//                    // 传入模拟 ViewModel
//                    timeViewModel = mockTimeViewModel,
//                    timeBarViewModel = mockTimeBarViewModel,
//                    completedTaskViewModel = mockCompletedTaskViewModel,
//                    medicineTakingStateViewModel = mockMedicineStateViewModel
//                )
//            }
//        }
//    }
//}