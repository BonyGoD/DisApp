package com.example.presupuestosdisa.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.componentes.ComponenteMedidas
import com.example.presupuestosdisa.componentes.ComponenteMenu
import com.example.presupuestosdisa.componentes.ComponenteSelectores
import com.example.presupuestosdisa.ui.theme.DisaPink

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro)
)

val itemsTipoVentana = listOf("Practicable", "Corredera", "Elevable")
val itemsTipoSerie = listOf("RPT", "Fria")
val itemsColores = listOf("Blanco", "Ral estandar", "Imitacion madera")
val itemsTipoVidrio = listOf("4/20/4", "4+4/16/4", "3+3/16/6", "4+4")
val itemsTipoPersiana = listOf("R45", "C45", "MonoBlock")
val itemsTipoRegistro = listOf("Chapa de aluminio", "Chapa Sandwich de aluminio")

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPresupuesto(navController: NavController) {

    val selectedTipoVentana = remember { mutableStateOf("Tipo de ventana") }
    val selectedTipoVidrio = remember { mutableStateOf("Tipo de vidrio") }
    val selectedTipoPersiana = remember { mutableStateOf("Tipo de persiana") }
    val selectedTipoRegistro = remember { mutableStateOf("Tipo de registro") }
    val selectedTipoSerie = remember { mutableStateOf("Tipo de serie") }

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
        ListaProductos(productos, selectedTipoVentana, selectedTipoSerie, selectedTipoVidrio, selectedTipoPersiana, selectedTipoRegistro)
    }
}

@Composable
fun ListaProductos(
    productos: List<Productos>,
    selectedTipoVentana: MutableState<String>,
    selectedTipoSerie: MutableState<String>,
    selectedTipoVidrio: MutableState<String>,
    selectedTipoPersiana: MutableState<String>,
    selectedTipoRegistro: MutableState<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(productos) { tipoProducto ->
            var expandida by remember { mutableStateOf(false) }
            val selectedColor = remember { mutableStateOf("Color") }

            Column {
                ComponenteMenu(tipoProducto.nombre, tipoProducto.icono) {
                    expandida = !expandida
                }
                if (expandida) {
                    ComponenteSelectores(
                        selectedTipoVentana,
                        selectedTipoVidrio,
                        selectedTipoPersiana,
                        selectedTipoRegistro,
                        selectedTipoSerie,
                        selectedColor,
                        tipoProducto.nombre
                    )
                    ComponenteMedidas()
                }
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            Text(text = "AÑADIR")
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showSystemUi = true)
@Composable
fun PreviewPantallaPresupuesto() {
    ListaProductos(productos, selectedTipoVentana = mutableStateOf("Tipo de ventana"),
        selectedTipoVidrio = mutableStateOf("Tipo de vidrio"),
        selectedTipoPersiana = mutableStateOf("Tipo de persiana"),
        selectedTipoRegistro = mutableStateOf("Tipo de registro"),
        selectedTipoSerie = mutableStateOf("Tipo de serie"))
}
