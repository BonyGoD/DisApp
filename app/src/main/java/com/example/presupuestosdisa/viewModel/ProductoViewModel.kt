package com.example.presupuestosdisa.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.presupuestosdisa.model.Colores
import com.example.presupuestosdisa.model.Persiana
import com.example.presupuestosdisa.model.Registro
import com.example.presupuestosdisa.model.Serie
import com.example.presupuestosdisa.model.Vidrio

class ProductoViewModel: ViewModel() {

    val tipoVentana = mutableStateOf(
        listOf(
            "Practicable",
            "Corredera",
            "Elevable"
        ))
    val listaTipoVentana: State<List<String>> = tipoVentana

    private val tipoVidrio = mutableStateOf(
        listOf(
            Vidrio("4/20/4", 85),
            Vidrio("4+4/16/4", 103),
            Vidrio("3+3/16/6", 99),
            Vidrio("4+4", 45)
        ))
    val listaTipoVidrio: State<List<Vidrio>> = tipoVidrio

    val tipoPersiana = mutableStateOf(
        listOf(
            Persiana("Aluminio", 50),
            Persiana("PVC", 40),
            Persiana("Madera", 60)
        ))
    val listaTipoPersiana: State<List<Persiana>> = tipoPersiana

    val tipoRegistro = mutableStateOf(
        listOf(
        Registro("Chapa aluminio", 45),
        Registro("Chapa sandwich", 90)
        ))
    val listaTipoRegistro: State<List<Registro>> = tipoRegistro

    val itemsTipoSerie = mutableStateOf(
        listOf(
            Serie("RPT", 94),
            Serie("Fria", 64)
        ))
    val listaTipoSerie: State<List<Serie>> = itemsTipoSerie

    private val colores = mutableStateOf(
        listOf(
            Colores("Blanco", 0),
            Colores("Ral estandar", 10),
            Colores("Madera", 20)
        ))
    val listaColores: State<List<Colores>> = colores

}