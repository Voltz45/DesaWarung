package com.desacode.desawarung.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desacode.desawarung.screens.WarungSplashScreen
import com.desacode.desawarung.screens.home.WarungHomeScreen
import com.desacode.desawarung.screens.login.WarungLoginScreen

@Composable
fun WarungNavigation() {
    val navController = rememberNavController() 
    NavHost(navController = navController, startDestination = WarungScreens.SplashScreen.name) {
        composable(WarungScreens.SplashScreen.name) {
            WarungSplashScreen(navController = navController)
        }

        composable(WarungScreens.LoginScreen.name) {
            BackHandler(true) {
                Log.i("BACK_BUTTON", "WarungNavigation: Back button is have no function in login screen")
            }
            WarungLoginScreen(navController)
        }

        composable(WarungScreens.HomeScreen.name) {
            BackHandler(true) {
                Log.i("BACK_BUTTON", "WarungNavigation: Back button is have no function in login screen")
            }
            WarungHomeScreen(navController)
        }
    }
}