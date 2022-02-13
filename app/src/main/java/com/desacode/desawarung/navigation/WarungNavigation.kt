package com.desacode.desawarung.navigation

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
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

@ExperimentalComposeUiApi
@Composable
fun WarungNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WarungScreens.SplashScreen.name) {
        composable(WarungScreens.SplashScreen.name) {
            WarungSplashScreen(navController = navController)
        }

        composable(WarungScreens.LoginScreen.name) {
            BackHandler(true) {
                Log.i(
                    "BACK_BUTTON",
                    "WarungNavigation: Back button is have no function in login screen"
                )
            }
            WarungLoginScreen(navController)
        }

        composable(WarungScreens.HomeScreen.name) {
            val activity = (LocalContext.current as? Activity)
            val counterBack = remember {
                mutableStateOf(0)
            }
            val context = LocalContext.current
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
            WarungHomeScreen(navController)
        }
    }
}