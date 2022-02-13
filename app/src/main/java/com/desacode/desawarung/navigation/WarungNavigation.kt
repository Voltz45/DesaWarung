package com.desacode.desawarung.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desacode.desawarung.screens.WarungSplashScreen
import com.desacode.desawarung.screens.home.WarungHomeScreen
import com.desacode.desawarung.screens.login.WarungLoginScreen
import com.desacode.desawarung.screens.notification.WarungNotificationScreen
import com.desacode.desawarung.utils.BackButtonHandler

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
            WarungHomeScreen(navController)
        }

        composable(WarungScreens.NotificationScreen.name) {
            BackHandler {
                navController.popBackStack()
            }
            WarungNotificationScreen(navController)
        }
    }
}