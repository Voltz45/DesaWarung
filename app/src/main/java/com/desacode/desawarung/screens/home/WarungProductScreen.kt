package com.desacode.desawarung.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.desacode.desawarung.R
import com.desacode.desawarung.components.WarungTopBar
import com.desacode.desawarung.ui.theme.BlueDesa
import com.desacode.desawarung.ui.theme.CursorColor
import com.desacode.desawarung.ui.theme.IconColor

@Composable
fun WarungHomeScreen(navController: NavController) {
    /* TODO:Refactoring Code */
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = BlueDesa,
                onClick = { /* TODO:Create Open Camera Service */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.scanner_icon),
                    contentDescription = "scan_icon",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                ),
                modifier = Modifier
                    .height(65.dp),
                backgroundColor = Color.White,
                elevation = 10.dp,
                contentPadding = PaddingValues(2.dp)
            ) {
                BottomNavigation(
                    modifier = Modifier
                        .height(100.dp),
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    BottomNavigationItem(
                        selected = true,
                        selectedContentColor = BlueDesa,
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.product_icon),
                                contentDescription = "test",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = {
                            Text(text = "Product", fontWeight = FontWeight.SemiBold)
                        },
                        onClick = { /*TODO*/ }
                    )
                    BottomNavigationItem(
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.distributor_icon),
                                contentDescription = "test",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = {
                            Text(text = "Distributor", fontWeight = FontWeight.SemiBold)
                        },
                        onClick = { /*TODO*/ }
                    )
                    BottomNavigationItem(
                        selected = false,
                        icon = {},
                        onClick = { /*TODO*/ }
                    )
                    BottomNavigationItem(
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.cart_icon),
                                contentDescription = "test",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = {
                            Text(text = "Cart", fontWeight = FontWeight.SemiBold)
                        },
                        onClick = { /*TODO*/ }
                    )
                    BottomNavigationItem(
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.setting_icon),
                                contentDescription = "test",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = {
                            Text(text = "Settings", fontWeight = FontWeight.SemiBold)
                        },
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    ) {
        //TopAppBar
        WarungTopBar(modifier = Modifier.padding(20.dp), navController)
    }
}