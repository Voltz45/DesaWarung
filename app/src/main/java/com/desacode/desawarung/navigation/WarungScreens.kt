package com.desacode.desawarung.navigation

import java.lang.IllegalArgumentException

enum class WarungScreens {
    SplashScreen,
    LoginScreen,
    ProductScreen,
    NotificationScreen;

    companion object {
        fun fromRoute(route: String?): WarungScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            ProductScreen.name -> ProductScreen
            NotificationScreen.name -> NotificationScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}