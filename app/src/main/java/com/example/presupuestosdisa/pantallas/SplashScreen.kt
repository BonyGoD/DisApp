package com.example.presupuestosdisa.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.navegacion.AppPantallas
import com.example.presupuestosdisa.ui.theme.DisaPink
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(AppPantallas.PantallaPrincipal.ruta)
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

@Preview(showSystemUi = true)
@Composable
fun PreviewSplashScreen(){
    Splash()
}