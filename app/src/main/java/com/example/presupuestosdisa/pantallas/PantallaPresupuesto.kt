package com.example.presupuestosdisa.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.componentes.ComponenteMedidas
import com.example.presupuestosdisa.componentes.ComponenteMenu
import com.example.presupuestosdisa.componentes.ComponenteSelectores
import com.example.presupuestosdisa.componentes.TextFieldComponent
import com.example.presupuestosdisa.model.Ventana
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.utils.MedidasState

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro),
)

val itemsTipoVentana = listOf("Practicable", "Corredera", "Elevable")
val itemsTipoSerie = listOf("RPT", "Fria")
val itemsColores = listOf("Blanco", "Ral estandar", "Imitacion madera")
val itemsTipoVidrio = listOf("4/20/4", "4+4/16/4", "3+3/16/6", "4+4")
val itemsTipoPersiana = listOf("R45", "C45", "MonoBlock")
val itemsTipoRegistro = listOf("Chapa de aluminio", "Chapa Sandwich de aluminio")
var ventana = Ventana()
val arrowUp = R.drawable.arrow_up
val arrowDown = R.drawable.arrow_down

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPresupuesto(navController: NavController) {

    val selectedTipoVentana = remember { mutableStateOf("Tipo de Ventana") }
    val selectedTipoVidrio = remember { mutableStateOf("Tipo de Vidrio") }
    val selectedTipoPersiana = remember { mutableStateOf("Tipo de Persiana") }
    val selectedTipoRegistro = remember { mutableStateOf("Tipo de Registro") }
    val selectedTipoSerie = remember { mutableStateOf("Serie") }
    val selectedColorVentana = remember { mutableStateOf("Color") }
    val selectedColorPersiana = remember { mutableStateOf("Color") }
    val checkboxStateVentana = remember { mutableStateOf(false) }
    val checkboxStatePersiana = remember { mutableStateOf(false) }
    val medidasState = remember {
        mutableListOf(
            MedidasState("Ventana"),
            MedidasState("Vidrio"),
            MedidasState("Persiana"),
            MedidasState("Registro")
        )
    }

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
        ListaProductos(productos, selectedTipoVentana, selectedTipoSerie, selectedTipoVidrio, selectedTipoPersiana, selectedTipoRegistro, selectedColorVentana, selectedColorPersiana, checkboxStateVentana, checkboxStatePersiana, medidasState)
    }
}

@Composable
fun ListaProductos(
    productos: List<Productos>,
    selectedTipoVentana: MutableState<String>,
    selectedTipoSerie: MutableState<String>,
    selectedTipoVidrio: MutableState<String>,
    selectedTipoPersiana: MutableState<String>,
    selectedTipoRegistro: MutableState<String>,
    selectedColorVentana: MutableState<String>,
    selectedColorPersiana: MutableState<String>,
    checkboxStateVentana: MutableState<Boolean>,
    checkboxStatePersiana: MutableState<Boolean>,
    medidasState: MutableList<MedidasState>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 150.dp)
        ) {
            items(productos) { tipoProducto ->
                var expandida by remember { mutableStateOf(false) }

                Column {
                    ComponenteMenu(tipoProducto.nombre, tipoProducto.icono, if (expandida) { arrowUp } else { arrowDown }) {
                        expandida = !expandida
                    }
                    if (expandida) {
                        ComponenteSelectores(
                            selectedTipoVentana,
                            selectedTipoVidrio,
                            selectedTipoPersiana,
                            selectedTipoRegistro,
                            selectedTipoSerie,
                            selectedColorVentana,
                            selectedColorPersiana,
                            checkboxStateVentana,
                            checkboxStatePersiana,
                            medidasState,
                            tipoProducto.nombre
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    ventana.tipoVentana = selectedTipoVentana.value
                    ventana.tipoSerie = selectedTipoSerie.value
                    ventana.tipoColor = selectedColorVentana.value
                    ventana.oscilobatiente = checkboxStateVentana.value
                }
            ) {
                Text(
                    text = "AÑADIR",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
