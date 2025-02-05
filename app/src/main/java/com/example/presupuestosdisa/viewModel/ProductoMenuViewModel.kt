package com.example.presupuestosdisa.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presupuestosdisa.model.Colores
import com.example.presupuestosdisa.model.Persiana
import com.example.presupuestosdisa.model.ProductoMenu
import com.example.presupuestosdisa.model.Registro
import com.example.presupuestosdisa.model.Serie
import com.example.presupuestosdisa.model.Ventana
import com.example.presupuestosdisa.model.Vidrio
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductoMenuViewModel() : ViewModel() {

    private var db: FirebaseFirestore = Firebase.firestore

    private val _producto = MutableStateFlow<List<ProductoMenu>>(emptyList())
    val producto: StateFlow<List<ProductoMenu>> = _producto

    private val _tipoVentana = MutableStateFlow<List<Ventana>>(emptyList())
    val tipoVentana = _tipoVentana

    private val _tipoVidrio = MutableStateFlow<List<Vidrio>>(emptyList())
    val tipoVidrio = _tipoVidrio

    private val _tipoPersiana = MutableStateFlow<List<Persiana>>(emptyList())
    val tipoPersiana = _tipoPersiana

    private val _tipoRegistro = MutableStateFlow<List<Registro>>(emptyList())
    val tipoRegistro = _tipoRegistro

    private val _tipoSerie = MutableStateFlow<List<Serie>>(emptyList())
    val tipoSerie = _tipoSerie

    private val _colores = MutableStateFlow<List<Colores>>(emptyList())
    val colores = _colores

    init {
        getProductosMenu()
        getTipoVentana()
        getTipoVidrio()
        getTipoPersiana()
        getTipoRegistro()
        getTipoSerie()
        getColores()
    }

    private fun getProductosMenu() {
        viewModelScope.launch {
            val result: List<ProductoMenu> = withContext(Dispatchers.IO) {
                getAllProductosMenu()
            }
            _producto.value = result
        }
    }

    private fun getTipoVentana() {
        viewModelScope.launch {
            val result: List<Ventana> = withContext(Dispatchers.IO) {
                getAllTipoVentana()
            }
            _tipoVentana.value = result
        }
    }

    private fun getTipoVidrio() {
        viewModelScope.launch {
            val result: List<Vidrio> = withContext(Dispatchers.IO) {
                getAllTipoVidrio()
            }
            _tipoVidrio.value = result
        }
    }

    private fun getTipoPersiana() {
        viewModelScope.launch {
            val result: List<Persiana> = withContext(Dispatchers.IO) {
                getAllTipoPersiana()
            }
            _tipoPersiana.value = result
        }
    }

    private fun getTipoRegistro() {
        viewModelScope.launch {
            val result: List<Registro> = withContext(Dispatchers.IO) {
                getAllTipoRegistro()
            }
            _tipoRegistro.value = result
        }
    }

    private fun getTipoSerie() {
        viewModelScope.launch {
            val result: List<Serie> = withContext(Dispatchers.IO) {
                getAllTipoSerie()
            }
            _tipoSerie.value = result
        }
    }

    private fun getColores() {
        viewModelScope.launch {
            val result: List<Colores> = withContext(Dispatchers.IO) {
                getAllColores()
            }
            _colores.value = result
        }
    }

    private suspend fun getAllProductosMenu(): List<ProductoMenu> {
        return try {
            db.collection("productos")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(ProductoMenu::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllTipoVentana(): List<Ventana> {
        return try {
            db.collection("tipoVentana")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Ventana::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllTipoVidrio(): List<Vidrio> {
        return try {
            db.collection("tipoVidrio")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Vidrio::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllTipoPersiana(): List<Persiana> {
        return try {
            db.collection("tipoPersiana")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Persiana::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllTipoRegistro(): List<Registro> {
        return try {
            db.collection("tipoRegistro")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Registro::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllTipoSerie(): List<Serie> {
        return try {
            db.collection("tipoSerie")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Serie::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }

    private suspend fun getAllColores(): List<Colores> {
        return try {
            db.collection("colores")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Colores::class.java)
                }
        } catch (e: Exception) {
            Log.e("ProductoViewModel", "Error al obtener productosMenu", e)
            emptyList()
        }
    }
}