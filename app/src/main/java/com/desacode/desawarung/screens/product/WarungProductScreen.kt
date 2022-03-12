package com.desacode.desawarung.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.desacode.desawarung.components.*
import com.desacode.desawarung.navigation.WarungBottomNavItems
import com.desacode.desawarung.ui.theme.IconColor

@ExperimentalFoundationApi
@Composable
fun WarungProductScreen(navController: NavController) {
    /* TODO:Refactoring Code */

    val navItems = listOf(
        WarungBottomNavItems.Product,
        WarungBottomNavItems.Distributor,
        WarungBottomNavItems.Empty,
        WarungBottomNavItems.Cart,
        WarungBottomNavItems.Setting
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val keyboardController = LocalFocusManager.current
    val searchState = remember {
        mutableStateOf("")
    }
    val expandedState = remember {
        mutableStateOf(false)
    }
    val filterState = remember { mutableStateOf("") }
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val divideWidth = (screenWidth - 40.dp - 15.dp) / 2

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FabButton(navController = navController)
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            WarungBottomBar(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                navItems = navItems,
                currentRoute = currentRoute
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
        ) {
            item {
                //TopAppBar
                WarungTopBar(
                    modifier = Modifier.padding(top = 20.dp, bottom = 30.dp, start = 20.dp, end = 20.dp),
                    navController
                )
            }

            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchBar(searchState = searchState, keyboardController = keyboardController)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "145 Result", color = IconColor.copy(0.8f))

                    Column {
                        DropDownMenu(filterState = filterState, expandedState = expandedState)
                    }
                }
            }

            /* TODO: Need to Refactoring code */
            items(4) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 15.dp, top = 0.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    ProductCard(divideWidth = divideWidth)

                    Spacer(modifier = Modifier.width(15.dp))

                    ProductCard(divideWidth = divideWidth)
                }
            }
        }
    }
}