package com.example.presupuestosdisa.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.DisaPink
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToPantallaPrincipal: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        navigateToPantallaPrincipal()
    }
    Splash()
}

@Composable
fun Splash() {

    Column(
        modifier = Modifier
            .background(color = DisaPink)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logodisa),
            contentDescription = "Logo de Disa",
            modifier = Modifier.size(200.dp)
        )
    }
}