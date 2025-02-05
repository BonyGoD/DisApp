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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.componentes.ComponenteMenu
import com.example.presupuestosdisa.componentes.ComponenteSelectores
import com.example.presupuestosdisa.model.Colores
import com.example.presupuestosdisa.model.MedidasState
import com.example.presupuestosdisa.model.Persiana
import com.example.presupuestosdisa.model.Producto
import com.example.presupuestosdisa.model.ProductoMenu
import com.example.presupuestosdisa.model.Registro
import com.example.presupuestosdisa.model.Serie
import com.example.presupuestosdisa.model.Ventana
import com.example.presupuestosdisa.model.Vidrio
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.viewModel.ProductoMenuViewModel
import com.example.presupuestosdisa.viewModel.ProductoViewModel
import com.example.presupuestosdisa.viewModel.SharedViewModel

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro),
)

val arrowUp = R.drawable.arrow_up
val arrowDown = R.drawable.arrow_down

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPresupuesto(sharedViewModel: SharedViewModel, navigateBack:() -> Unit) {

    val productoMenuViewModel= ProductoMenuViewModel()
    val productoViewModel = ProductoViewModel()

    val producto: State<List<ProductoMenu>> = productoMenuViewModel.producto.collectAsState()
    val tipoVentana: State<List<Ventana>> = productoMenuViewModel.tipoVentana.collectAsState()
    val tipoVidrio: State<List<Vidrio>> = productoMenuViewModel.tipoVidrio.collectAsState()
    val tipoPersiana: State<List<Persiana>> = productoMenuViewModel.tipoPersiana.collectAsState()
    val tipoRegistro: State<List<Registro>> = productoMenuViewModel.tipoRegistro.collectAsState()
    val tipoSerie: State<List<Serie>> = productoMenuViewModel.tipoSerie.collectAsState()
    val colores: State<List<Colores>> = productoMenuViewModel.colores.collectAsState()

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

    val productosList = remember { mutableStateListOf<Producto>() }

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
                            navigateBack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DisaPink
                )
            )
        }
    ) {
        ListaProductos(sharedViewModel, navigateBack, productos, selectedTipoVentana, selectedTipoSerie, selectedTipoVidrio, selectedTipoPersiana, selectedTipoRegistro, selectedColorVentana, selectedColorPersiana, checkboxStateVentana, checkboxStatePersiana, medidasState, productosList)
    }
}

@Composable
fun ListaProductos(
    sharedViewModel: SharedViewModel,
    navigateBack: () -> Unit,
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
    productosList: MutableList<Producto>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 150.dp, bottom = 30.dp)
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
                            tipoProducto.nombre,
                            productosList
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
                    productosList.forEach { producto ->
                        sharedViewModel.agregarProducto(producto)
                    }
                    navigateBack()
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
