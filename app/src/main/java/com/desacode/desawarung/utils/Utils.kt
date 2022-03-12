package com.desacode.desawarung.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory

@ExperimentalPermissionsApi
fun PermissionState.permanentlyDeclined(): Boolean {
    return !shouldShowRationale && !hasPermission
}

@ExperimentalPermissionsApi
@Composable
fun permissionsState(): MultiplePermissionsState {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionState.launchMultiplePermissionRequest()
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )

    return permissionState
}

@ExperimentalPermissionsApi
@Composable
fun ShowPermissionState() {
    permissionsState().permissions.forEach { perm ->
        when (perm.permission) {
            Manifest.permission.CAMERA -> {
                when {
                    perm.hasPermission -> {
                        Text(text = "Camera permission accepted")
                    }
                    perm.shouldShowRationale -> {
                        Text(text = "Camera permission is needed")
                    }
                    perm.permanentlyDeclined() -> {
                        Text(
                            text = "Camera permission permanently declined" +
                                    "if u want to enabled it, please give access" +
                                    "from settings"
                        )
                    }
                }
            }

            Manifest.permission.RECORD_AUDIO -> {
                when {
                    perm.hasPermission -> {
                        Text(text = "Record audio permission accepted")
                    }
                    perm.shouldShowRationale -> {
                        Text(text = "Record audio permission is needed")
                    }
                    perm.permanentlyDeclined() -> {
                        Text(
                            text = "Record audio permission permanently declined" +
                                    "if u want to enabled it, please give access" +
                                    "from settings"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeSystemBarColor(
    systemBarColor: Color? = null,
    statusBarColor: Color? = null,
    navBarColor: Color? = null,
    hideNavBar: Boolean = true
) {
    val systemUiController = rememberSystemUiController()

    if (!hideNavBar || hideNavBar) {
        systemUiController.isNavigationBarVisible = hideNavBar
    }

    if (isSystemInDarkTheme()) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    } else {
        if (statusBarColor != null && navBarColor != null) {
            systemUiController.setNavigationBarColor(
                color = navBarColor
            )
            systemUiController.setStatusBarColor(
                color = statusBarColor
            )
        }
        if (systemBarColor != null) {
            systemUiController.setSystemBarsColor(
                color = systemBarColor
            )
        }
    }
}

@Composable
fun BackButtonHandler(activity: Activity?, counterBack: MutableState<Int>, context: Context) {
    BackHandler(true) {
        counterBack.value += 1
        if (counterBack.value > 1) activity?.finish()
        else Toast.makeText(
            context,
            "Please Click again to close this app",
            Toast.LENGTH_LONG
        ).show()

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable { counterBack.value = 0 },
            10000
        )
    }
}

fun ScannerView() {

}