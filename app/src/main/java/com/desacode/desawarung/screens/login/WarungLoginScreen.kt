package com.desacode.desawarung.screens.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.desacode.desawarung.components.*
import com.desacode.desawarung.navigation.WarungScreens
import com.desacode.desawarung.utils.*

@ExperimentalComposeUiApi
@Composable
fun WarungLoginScreen(navController: NavController) {
    val emailState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val check = rememberSaveable { mutableStateOf(false) }
    val passVisibility = rememberSaveable { mutableStateOf(false) }
    val valid = remember(emailState.value, passwordState.value) {
        emailState.value.trim().isNotEmpty() && passwordState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalFocusManager.current
    val passwordFocusRequest = FocusRequester.Default

    ChangeSystemBarColor(statusBarColor = Color.Black, navBarColor = Color.Transparent)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.Start
        ) {
            item {
                WarungLogo()

                Spacer(modifier = Modifier.height(50.dp))

                Greeting(
                    greeting = "Welcome",
                    caption = "Sign in to continue."
                )

                Spacer(modifier = Modifier.height(60.dp))

                EmailInput(
                    emailState = emailState,
                    enabled = true,
                    onAction = KeyboardActions { passwordFocusRequest.requestFocus() }
                )

                Spacer(modifier = Modifier.height(10.dp))

                PasswordInput(
                    modifier = Modifier.focusRequester(passwordFocusRequest),
                    passwordState = passwordState,
                    placeHolder = "Password",
                    enabled = true,
                    passwordVisibility = passVisibility,
                    onAction = KeyboardActions {
                        if (!valid) return@KeyboardActions
                        keyboardController.clearFocus()
                    }
                )

                Spacer(
                    modifier = Modifier
                        .mediaQuery(
                            Dimensions.Height lessThan 760.dp,
                            modifier = Modifier.height(150.dp)
                        )
                        .mediaQuery(
                            Dimensions.Height greaterThan 760.dp,
                            modifier = Modifier.height(180.dp)
                        )
                )

                CheckBox(checkState = check, caption = "Remember me next time")

                Spacer(modifier = Modifier.height(25.dp))

                SignButton(
                    label = "Sign In",
                    showIcon = true
                ) {
                    navController.navigate(WarungScreens.ProductScreen.name)
                }
            }
        }
    }
}

