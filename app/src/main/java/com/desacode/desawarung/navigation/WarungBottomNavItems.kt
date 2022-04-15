package com.desacode.desawarung.navigation

import com.desacode.desawarung.R

sealed class WarungBottomNavItems(var route: String = "", var icon: Int = 0, var title: String = "", var enabled: Boolean = true) {
    object Product: WarungBottomNavItems(WarungScreens.ProductScreen.name, R.drawable.product_icon, "Product")

    /*TODO: Need to add specific route for each item */
    object Distributor: WarungBottomNavItems(WarungScreens.DistributorScreen.name, R.drawable.distributor_icon, "Distributor")
    object Empty: WarungBottomNavItems(enabled = false)
    object Cart: WarungBottomNavItems(WarungScreens.CartScreen.name, R.drawable.cart_icon, "Cart")
    object Setting: WarungBottomNavItems(WarungScreens.SettingScreen.name, R.drawable.setting_icon, "Setting")
}
