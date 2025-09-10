package com.devware.disapp.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devware.disapp.R
import com.devware.disapp.data.model.ConstantesTipos.PERSIANA
import com.devware.disapp.data.model.ConstantesTipos.REGISTRO
import com.devware.disapp.data.model.ConstantesTipos.VENTANA
import com.devware.disapp.data.model.ConstantesTipos.VIDRIO
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
    Productos(VENTANA, R.drawable.ventana_menu),
    Productos(VIDRIO, R.drawable.vidrio_menu),
    Productos(PERSIANA, R.drawable.persiana_menu),
    Productos(REGISTRO, R.drawable.registro_menu),
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
                        text = stringResource(R.string.volver),
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
    var showDialog by remember { mutableStateOf(false) }

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
                val listaProductos = LogicaAgregarProductos().getProductList()

                if (listaProductos.any { !it.esValido() } || listaProductos.isEmpty()) {
                    showDialog = true
                } else {
                    sharedViewModel.agregarListaProductos(listaProductos.map { it.copy() })
                    LogicaAgregarProductos().eliminarProductos()
                    navigateBack()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = stringResource(R.string.añadir),
                fontSize = 23.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        if(showDialog){
            Dialog(
                onDismissRequest = { showDialog = false }
                ){
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        ){
                        Text(
                            text = stringResource(R.string.no_seleccionado_sin_propiedades),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))//
                        Button(
                            onClick = { showDialog = false },
                        ) {
                            Text(text = stringResource(R.string.aceptar))
                        }
                    }
                }
            }
        }
    }
}
