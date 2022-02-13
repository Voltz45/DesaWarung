package com.desacode.desawarung.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.desacode.desawarung.R
import com.desacode.desawarung.navigation.WarungScreens
import com.desacode.desawarung.ui.theme.BlueDesa
import com.desacode.desawarung.utils.ChangeSystemBarColor
import kotlinx.coroutines.delay

@Composable
fun WarungSplashScreen(navController: NavHostController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)
        navController.navigate(WarungScreens.LoginScreen.name)
    }

    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    ChangeSystemBarColor(systemBarColor = BlueDesa, hideNavBar = false)
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else BlueDesa)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = R.drawable.desa_logo),
                contentDescription = "Logo Icon",
                modifier = Modifier
                    .size(50.dp)
                    .alpha(alpha = alpha),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = "Desacode",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 5.dp)
                    .alpha(alpha)
            )
        }
    }
}