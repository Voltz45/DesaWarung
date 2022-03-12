package com.desacode.desawarung.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desacode.desawarung.screens.WarungSplashScreen
import com.desacode.desawarung.screens.cameraView.WarungCameraView
import com.desacode.desawarung.screens.cart.WarungCartScreen
import com.desacode.desawarung.screens.distributor.WarungDistributorScreen
import com.desacode.desawarung.screens.login.WarungLoginScreen
import com.desacode.desawarung.screens.notification.WarungNotificationScreen
import com.desacode.desawarung.screens.product.WarungAddProductScreen
import com.desacode.desawarung.screens.product.WarungProductScreen
import com.desacode.desawarung.screens.setting.WarungSettingScreen
import com.desacode.desawarung.utils.BackButtonHandler
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@Composable
fun WarungNavigation() {
    val navController = rememberNavController()
    val activity = (LocalContext.current as? Activity)
    val counterBack = remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = WarungScreens.SplashScreen.name) {
        composable(WarungScreens.SplashScreen.name) {
            WarungSplashScreen(navController = navController)
        }

        composable(WarungScreens.LoginScreen.name) {
            BackButtonHandler(activity = activity, counterBack = counterBack, context = context)
            WarungLoginScreen(navController)
        }

        composable(WarungScreens.ProductScreen.name) {
            BackButtonHandler(activity = activity, counterBack = counterBack, context = context)
            WarungProductScreen(navController)
        }

        composable(WarungScreens.AddProductScreen.name) {
            BackButtonHandler(activity = activity, counterBack = counterBack, context = context)
            WarungAddProductScreen(navController)
        }

        composable(WarungScreens.DistributorScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungDistributorScreen(navController)
        }

        composable(WarungScreens.CartScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungCartScreen(navController)
        }

        composable(WarungScreens.SettingScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungSettingScreen(navController)
        }

        composable(WarungScreens.NotificationScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungNotificationScreen(navController)
        }

        composable(WarungScreens.CameraViewScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungCameraView(navController)
        }
    }
}