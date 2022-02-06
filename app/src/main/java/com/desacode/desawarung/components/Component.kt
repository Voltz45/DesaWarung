package com.desacode.desawarung.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.desacode.desawarung.R

@Composable
fun WarungLogo() {
    Image(
        painterResource(id = R.drawable.desa_logo),
        contentDescription = "Logo Icon",
        modifier = Modifier
            .size(50.dp),
    )
}