package com.example.presupuestosdisa.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.data.model.SelectablesPresupuestos
import com.example.presupuestosdisa.data.model.rememberSelectablesPresupuestos
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.ui.view.componentes.ComponenteMenu
import com.example.presupuestosdisa.ui.view.componentes.ComponenteSelectores
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.ui.viewModel.SharedViewModel

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro),
)

val arrowUp = R.drawable.arrow_up
val arrowDown = R.drawable.arrow_down

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPresupuesto(
    fireBaseViewModel: FireBaseViewModel,
    sharedViewModel: SharedViewModel,
    navigateBack: () -> Unit
) {

    val selectablesPresupuestos = rememberSelectablesPresupuestos()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.Blue),
                title = {
                    Text(
                        text = "Presupuestos",
                        fontWeight = FontWeight.Bold,
                    )
                },
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
        ListaProductos(
            sharedViewModel,
            fireBaseViewModel,
            navigateBack,
            productos,
            selectablesPresupuestos
        )
    }
}

@Composable
fun ListaProductos(
    sharedViewModel: SharedViewModel,
    fireBaseViewModel: FireBaseViewModel,
    navigateBack: () -> Unit,
    productos: List<Productos>,
    selectablesPresupuestos: SelectablesPresupuestos
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

                ComponenteMenu(
                    tipoProducto.nombre, tipoProducto.icono, if (expandida) {
                        arrowUp
                    } else {
                        arrowDown
                    }
                ) {
                    expandida = !expandida
                }
                AnimatedVisibility(
                    expandida
                )
                {
                    ComponenteSelectores(
                        selectablesPresupuestos,
                        tipoProducto.nombre,
                        fireBaseViewModel
                    )
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
                    sharedViewModel.agregarListaProductos()
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
