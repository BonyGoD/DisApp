package com.example.presupuestosdisa.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presupuestosdisa.model.ProductoMenu
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

    init {
        getProductosMenu()
    }

    private fun getProductosMenu() {
        viewModelScope.launch {
            val result: List<ProductoMenu> = withContext(Dispatchers.IO) {
                getAllProductosMenu()
            }
            _producto.value = result
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
            Log.e("ProductoViewModel", "Error al obtener productos", e)
            emptyList()
        }
    }
}