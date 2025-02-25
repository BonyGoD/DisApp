package com.devware.disapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devware.disapp.data.model.ProductoInfo
import com.devware.disapp.domain.AccesoAppUseCase
import com.devware.disapp.domain.GetProductosUseCase
import com.devware.disapp.ui.view.MainUIState
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

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    val uiState: StateFlow<MainUIState> = _uiState

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
            _uiState.value = MainUIState.Loading
            try {
                val result = getProductosUseCase()
                if (result.isNotEmpty()) {
                    _uiState.value = MainUIState.Success(result)
                    _producto.value = result
                } else {
                    _uiState.value = MainUIState.Error("No hay productos")
                }
            } catch (e: Exception) {
                _uiState.value = MainUIState.Error("Error al cargar productos")
            }
        }
    }
}