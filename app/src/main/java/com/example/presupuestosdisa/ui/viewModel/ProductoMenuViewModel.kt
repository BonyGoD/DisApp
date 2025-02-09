package com.example.presupuestosdisa.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.domain.GetProductosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoMenuViewModel @Inject constructor(
    private val getProductosUseCase: GetProductosUseCase
) : ViewModel() {

    private val _producto = MutableLiveData<List<ProductoInfo>?>()
    val producto: MutableLiveData<List<ProductoInfo>?> get() = _producto

    fun onCreate() {
        viewModelScope.launch {
            val result = getProductosUseCase()

            if(!result.isNullOrEmpty()) {
                _producto.postValue(result)
            }
        }
    }
}