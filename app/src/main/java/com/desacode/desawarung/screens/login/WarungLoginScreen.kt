package com.desacode.desawarung.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.desacode.desawarung.components.WarungLogo
import com.desacode.desawarung.navigation.WarungScreens
import com.desacode.desawarung.ui.theme.BlueDesa
import com.desacode.desawarung.utils.ChangeSystemBarColor

@Composable
fun WarungLoginScreen(navController: NavController) {
    val state = rememberSaveable { mutableStateOf("") }
    val check = rememberSaveable { mutableStateOf(false) }
    ChangeSystemBarColor(statusBarColor = Color.Black, navBarColor = Color.Transparent)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(top = 30.dp),
            horizontalAlignment = Alignment.Start
        ) {
            WarungLogo()

            Spacer(modifier = Modifier.height(50.dp))

            Greeting(greeting = "Welcome", caption = "Sign in to continue.")

            Spacer(modifier = Modifier.height(60.dp))

            TextField(
                value = state.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color(0xfff0f0f0)
                ),
                onValueChange = { state.value = it },
                placeholder = { Text(text = "Email") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email_icon")
                },
                trailingIcon = {
                    if (state.value.isNotEmpty() && state.value.contains("@")) Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "check_icon",
                        tint = Color.Green
                    )
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = state.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color(0xfff0f0f0)
                ),
                onValueChange = { state.value = it },
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(text = "Password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Email_icon")
                },
                trailingIcon = {
                    if (state.value.isNotEmpty() && state.value.contains("@")) Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "check_icon",
                        tint = Color.Green
                    )
                }
            )

            Spacer(modifier = Modifier.height(240.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Checkbox(
                    checked = check.value,
                    onCheckedChange = { check.value = it },
                    modifier = Modifier.padding(end = 5.dp),
                    colors = CheckboxDefaults.colors(BlueDesa)
                )
                Text(text = "Remember me next time", style = MaterialTheme.typography.caption)
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(WarungScreens.HomeScreen.name) },
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(BlueDesa)
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Sign in", modifier = Modifier.padding(end = 5.dp, bottom = 2.dp),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "right",
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(greeting: String, caption: String) {
    Column {
        Text(
            text = greeting,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = caption,
            style = MaterialTheme.typography.caption
        )
    }
}