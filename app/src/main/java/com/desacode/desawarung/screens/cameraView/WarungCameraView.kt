package com.desacode.desawarung.screens.cameraView

import android.app.Activity
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.desacode.desawarung.R
import com.desacode.desawarung.navigation.WarungScreens
import com.desacode.desawarung.ui.theme.BlueDesa
import com.desacode.desawarung.utils.ShowPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

@ExperimentalPermissionsApi
@Composable
fun WarungCameraView(
    navController: NavHostController
) {
    ShowPermissionState()

    val text = remember {
        mutableStateOf("")
    }

    val scanStatus = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    val alertOpen = remember {
        mutableStateOf(false)
    }

    val root = LayoutInflater.from(context as Activity).inflate(R.layout.test, null)
    val barcodeView: DecoratedBarcodeView = root.findViewById(R.id.barcode_scanner)
    val formats = listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39)
    barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
    barcodeView.initializeFromIntent(context.intent)
    barcodeView.decodeContinuous { result ->
        result.text.let {
            text.value = it
        }

        alertOpen.value = true

        if (text.value.isNotEmpty()) barcodeView.pause()
    }

    DisposableEffect(key1 = scanStatus.value) {
        barcodeView.resume()
        onDispose {
            barcodeView.pause()
            if (text.value.isEmpty()) {
                navController.navigate(WarungScreens.ProductScreen.name)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                barcodeView.resume()
                root
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 1.dp
            ) {

                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null,
                    tint = BlueDesa,
                    modifier = Modifier
                        .clickable {
                            scanStatus.value = true
                        }
                        .size(40.dp)
                        .background(Color.White)
                )
            }

            Spacer(modifier = Modifier.width(75.dp))

            Text(
                text = "Product Scan",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        }

        if (alertOpen.value)
            AlertDialog(
                onDismissRequest = {
                    alertOpen.value = false
                },
                title = {
                    Text(text = "Confirmation Barcode", fontWeight = FontWeight.ExtraBold)
                },
                text = {
                    Text(text = "Fount product with code: ${text.value}\nDo you want to add this product?")
                },
                buttons = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { navController.navigate(WarungScreens.AddProductScreen.name) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = BlueDesa)
                        ) {
                            Text(text = "Add")
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = {
                                alertOpen.value = false
                                barcodeView.resume()

                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            )
    }
}
