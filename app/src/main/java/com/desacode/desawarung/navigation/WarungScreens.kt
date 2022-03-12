package com.desacode.desawarung.navigation

import java.lang.IllegalArgumentException

enum class WarungScreens {
    SplashScreen,
    LoginScreen,
    ProductScreen,
    AddProductScreen,
    DistributorScreen,
    CartScreen,
    SettingScreen,
    NotificationScreen,
    CameraViewScreen;

    companion object {
        fun fromRoute(route: String?): WarungScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            ProductScreen.name -> ProductScreen
            AddProductScreen.name -> AddProductScreen
            DistributorScreen.name -> DistributorScreen
            CartScreen.name -> CartScreen
            SettingScreen.name -> SettingScreen
            NotificationScreen.name -> NotificationScreen
            CameraViewScreen.name -> CameraViewScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}