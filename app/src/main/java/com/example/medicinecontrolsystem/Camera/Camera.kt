package com.example.medicinecontrolsystem

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOff
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.medicinecontrolsystem.data.patients
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(navController: NavController, patientId: Int?) {
    // 在 CameraScreen 中添加
    BackHandler {
        navController.popBackStack()
    }
    if (patientId == null) {
        ErrorScreen("无效的患者ID")
        return
    }
// 根据ID查找病人信息
    val patient = remember(patientId) {
        patients.find { it.id == patientId }
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val coroutineScope = rememberCoroutineScope()

    var isFlashOn by remember { mutableStateOf(false) }
    var cameraControl by remember { mutableStateOf<CameraControl?>(null) }
    var isScanning by remember { mutableStateOf(true) }

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var scannedBarcodes by remember { mutableStateOf(listOf<String>()) }

    val permissionGranted = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted.value = granted
    }

    if (!permissionGranted.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFFFFCF7), Color(0xFFE6F1FF)))),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("请授予相机权限以继续使用扫码功能")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                Text("请求权限")
            }
        }
        return
    }

    Scaffold(topBar = { TopAppBar(title = { Text("扫码核对") }) }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Brush.verticalGradient(listOf(Color(0xFFFFFCF7), Color(0xFFE6F1FF)))),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                TopHintText()
                Spacer(modifier = Modifier.height(16.dp))
                CameraPreviewBox(
                    isFlashOn = isFlashOn,
                    onCameraControlReady = { cameraControl = it },
                    onBarcodesScanned = { codes ->
                        if (!isScanning) return@CameraPreviewBox
                        isScanning = false

                        val cleanCodes = codes.filter { it.isNotBlank() }

                        when {
                            cleanCodes.size < 2 -> {
                                errorMessage = "未能识别两个条形码，请放入两个条形码"
                                scannedBarcodes = cleanCodes
                                showErrorDialog = true
                            }

                            cleanCodes[0] != cleanCodes[1] -> {
                                errorMessage = "两个条形码不一致，请确认药品信息后重新拍摄"
                                scannedBarcodes = cleanCodes
                                showErrorDialog = true
                            }

                            else -> {
                                playBeep()
                                navController.navigate("photo_submit/$patientId"){
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }

                        coroutineScope.launch {
                            delay(2000L)
                            isScanning = true
                        }
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                FlashlightToggleButton(
                    isFlashOn = isFlashOn,
                    onToggle = {
                        isFlashOn = !isFlashOn
                        cameraControl?.enableTorch(isFlashOn)
                    }
                )
            }

            if (showErrorDialog) {
                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("扫描失败") },
                    text = {
                        Column {
                            scannedBarcodes.forEachIndexed { index, code ->
                                Text("条码 ${index + 1}：$code")
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(errorMessage)
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            scannedBarcodes = emptyList()
                            showErrorDialog = false
                        }) {
                            Text("重新扫描")
                        }
                    }
                )
            }
        }
    }
}

// 顶部提示
@Composable
fun TopHintText() {
    Text(
        text = "请将桌面条形码与药盒条形码同时放入框内",
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black,
        modifier = Modifier.padding(16.dp)
    )
}

// 手电按钮
@Composable
fun FlashlightToggleButton(
    isFlashOn: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = if (isFlashOn) Icons.Default.FlashlightOn else Icons.Default.FlashlightOff,
            contentDescription = "闪光灯",
            tint = if (isFlashOn) Color.Yellow else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
    }
}

// 相机预览
@Composable
fun CameraPreviewBox(
    isFlashOn: Boolean,
    onCameraControlReady: (CameraControl) -> Unit,
    onBarcodesScanned: (List<String>) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            val previewView = androidx.camera.view.PreviewView(ctx).apply {
                scaleType = androidx.camera.view.PreviewView.ScaleType.FILL_CENTER
            }

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(Executors.newSingleThreadExecutor(), BarcodeAnalyzer(onBarcodesScanned))
                    }

                val camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageAnalysis
                )

                onCameraControlReady(camera.cameraControl)
                camera.cameraControl.enableTorch(isFlashOn)

            }, ContextCompat.getMainExecutor(context))

            previewView
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
    )
}

// 条码分析器
class BarcodeAnalyzer(
    private val onScanned: (List<String>) -> Unit
) : ImageAnalysis.Analyzer {
    private val scanner = BarcodeScanning.getClient()

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    val codes = barcodes.mapNotNull { it.rawValue?.trim() }
                    val uniqueCodes = codes.distinct()

                    val uniquePositions = barcodes
                        .mapNotNull { it.boundingBox }
                        .distinctBy { it.centerY() to it.centerX() }

                    val finalCodes = when {
                        uniqueCodes.size == 1 && uniquePositions.size >= 2 -> listOf(uniqueCodes[0], uniqueCodes[0])
                        uniqueCodes.size >= 2 -> uniqueCodes.take(2)
                        else -> uniqueCodes
                    }

                    if (finalCodes.isNotEmpty()) {
                        onScanned(finalCodes)
                    }
                }
                .addOnFailureListener { Log.e("Barcode", "识别失败", it) }
                .addOnCompleteListener { imageProxy.close() }
        } else {
            imageProxy.close()
        }
    }
}


// 提示音
fun playBeep() {
    try {
        val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150)
    } catch (e: Exception) {
        Log.e("Beep", "无法播放提示音", e)
    }
}