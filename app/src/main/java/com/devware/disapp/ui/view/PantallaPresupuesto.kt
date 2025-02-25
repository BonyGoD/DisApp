package com.devware.disapp.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devware.disapp.R
import com.devware.disapp.data.model.SelectablesPresupuestos
import com.devware.disapp.data.model.rememberSelectablesPresupuestos
import com.devware.disapp.ui.theme.BackgroundDisaColor
import com.devware.disapp.ui.theme.DisaPink
import com.devware.disapp.ui.viewModel.FireBaseViewModel
import com.devware.disapp.ui.viewModel.SharedViewModel
import com.devware.disapp.utils.LogicaAgregarProductos
import com.example.disapp.ui.view.componentes.ComponenteMenu
import com.example.disapp.ui.view.componentes.ComponenteSelectores

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana_menu),
    Productos("Vidrio", R.drawable.vidrio_menu),
    Productos("Persiana", R.drawable.persiana_menu),
    Productos("Registro", R.drawable.registro_menu),
)

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
                    .padding(bottom = 100.dp),
                title = {
                    Text(
                        text = "Volver",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navigateBack()
                        },
                        tint = Color.White
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDisaColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, bottom = 30.dp)
        ) {
            itemsIndexed(productos) {  index, tipoProducto ->
                var expandida by remember { mutableStateOf(false) }

                ComponenteMenu(
                    tipoProducto.nombre, tipoProducto.icono) {
                    expandida = !expandida
                }
                AnimatedVisibility(
                    expandida,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    ComponenteSelectores(
                        selectablesPresupuestos,
                        tipoProducto.nombre,
                        fireBaseViewModel
                    )
                }
                if(productos.size -1 != index){

                    Spacer(
                        modifier = Modifier.padding(horizontal = 150.dp, vertical = 10.dp).background(DisaPink)
                            .fillMaxWidth().size(0.5.dp)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
        Button(
            onClick = {
                val nuevosProductos = LogicaAgregarProductos().getProductList().map { it.copy() }
                sharedViewModel.agregarListaProductos(nuevosProductos)
                LogicaAgregarProductos().eliminarProductos()
                navigateBack()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = "Añadir",
                fontSize = 23.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
