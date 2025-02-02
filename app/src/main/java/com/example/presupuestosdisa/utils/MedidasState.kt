package com.example.presupuestosdisa.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MedidasState(
    val tipo: String,
    val valorAncho: MutableState<String> = mutableStateOf(""),
    val valorAlto: MutableState<String> = mutableStateOf("")
)
