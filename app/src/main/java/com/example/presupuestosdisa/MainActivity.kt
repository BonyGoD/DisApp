package com.example.presupuestosdisa

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalData(name = "Disa")
        }
    }

    @Composable
    private fun PersonalData(name: String){
        Column(
            modifier = Modifier
                .padding(10.dp, 25.dp)
                .fillMaxSize()
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hola que tal $name")
            Button(onClick = { /* Acción del botón */ }) {
                Text(text = "+ Añadir presupuesto")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewPersonalData() {
        PersonalData(name = "Disa")
    }
}