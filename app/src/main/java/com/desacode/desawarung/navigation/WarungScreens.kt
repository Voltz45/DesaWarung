package com.desacode.desawarung.navigation

import java.lang.IllegalArgumentException

enum class WarungScreens {
    SplashScreen,
    LoginScreen,
    HomeScreen;

    companion object {
        fun fromRoute(route: String?): WarungScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            HomeScreen.name -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}