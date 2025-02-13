package com.example.presupuestosdisa.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(fireBaseViewModel: FireBaseViewModel, navigateToPantallaPrincipal: () -> Unit) {

    val producto by fireBaseViewModel.producto.observeAsState()

    Splash()

    LaunchedEffect(producto) {
        if (producto != null) {
            navigateToPantallaPrincipal()
        }
    }
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