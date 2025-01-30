package com.example.presupuestosdisa.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.DisaPink

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro)
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPresupuesto(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.Blue),
                title = { Text(
                    text = "Presupuestos",
                    fontWeight = FontWeight.Bold,
                ) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DisaPink
                )
            )
        }
    ) {
        ListaProductos(productos)
    }
}

@Composable
fun ListaProductos(productos: List<Productos>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(50.dp, 150.dp, 50.dp, 0.dp),
    ) {
        items(productos) { material ->
            ComponentePrincipal(material.nombre, material.icono)
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun ComponentePrincipal(nombre: String, icono: Int) {

    var expandida by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickable {
                expandida = !expandida
            }
            .padding(5.dp),
    ) {
        Image(
            painterResource(icono),
            contentDescription = "Logo de Disa",
            modifier = Modifier
                .size(50.dp)
                .padding(5.dp))
        Text(
            text = nombre,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPantallaPresupuesto() {
    ListaProductos(productos)
}
