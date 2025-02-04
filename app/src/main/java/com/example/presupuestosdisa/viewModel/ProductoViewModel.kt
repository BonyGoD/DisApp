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
            Persiana("R45", 81),
            Persiana("C45", 81),
            Persiana("Monoblock", 95)
        ))
    val listaTipoPersiana: State<List<Persiana>> = tipoPersiana

    val tipoRegistro = mutableStateOf(
        listOf(
        Registro("Chapa aluminio", 45),
        Registro("Chapa sandwich", 90)
        ))
    val listaTipoRegistro: State<List<Registro>> = tipoRegistro

    val tipoSerie = mutableStateOf(
        listOf(
            Serie("RPT", 94),
            Serie("Fria", 64)
        ))
    val listaTipoSerie: State<List<Serie>> = tipoSerie

    private val colores = mutableStateOf(
        listOf(
            Colores("Blanco", 0.0),
            Colores("Ral estandar", 0.10),
            Colores("Imitacion madera", 0.50)
        ))
    val listaColores: State<List<Colores>> = colores

}