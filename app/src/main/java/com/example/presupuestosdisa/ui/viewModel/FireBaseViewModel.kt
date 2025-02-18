package com.example.presupuestosdisa.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.domain.AccesoAppUseCase
import com.example.presupuestosdisa.domain.GetProductosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireBaseViewModel @Inject constructor(
    private val getProductosUseCase: GetProductosUseCase,
    private val accesoAppUseCase: AccesoAppUseCase
) : ViewModel() {

    private val _producto = MutableLiveData<List<ProductoInfo>?>()
    val producto: MutableLiveData<List<ProductoInfo>?> = _producto

    private val _blockVersion = MutableStateFlow(false)
    val blockVersion: StateFlow<Boolean> = _blockVersion

    init {
        checkUserVersion()
        getProductos()
    }

    private fun checkUserVersion() {
        viewModelScope.launch {
            val result = accesoAppUseCase()

            if(!result){
                _blockVersion.value = !result
            }
        }
    }

    private fun getProductos() {
        viewModelScope.launch {
            val result = getProductosUseCase()

            if (result.isNotEmpty()) {
                _producto.postValue(result)
            }
        }
    }
}