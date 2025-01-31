package com.example.presupuestosdisa.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.DisaPink

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPrincipal(navController: NavController){
        VistaPrincipal(navController)
}
@Composable
fun VistaPrincipal(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DisaPink),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imagenDisa()
        Button(onClick = {
            navController.navigate("pantalla_presupuesto")
        }) {
            Text(text = "+ Añadir presupuesto")
        }
    }
}

@Composable
fun imagenDisa(){
    Image(
        painterResource(R.drawable.logodisa),
        contentDescription = "Logo de Disa",
        modifier = Modifier
            .padding(bottom = 25.dp)
            .size(200.dp)
            .clip(RoundedCornerShape(50.dp))
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPantallaPrincipal() {
    val navController = rememberNavController()
    PantallaPrincipal(navController)
}