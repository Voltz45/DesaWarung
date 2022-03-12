package com.desacode.desawarung

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.desacode.desawarung.navigation.WarungNavigation
import com.desacode.desawarung.ui.theme.DesaWarungTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.zxing.integration.android.IntentIntegrator


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesaWarungTheme {
                DesaWarungApp()
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun DesaWarungApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WarungNavigation()
        }
    }
}