package com.example.presupuestosdisa.ui.view.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R

@Composable
fun ComponenteMenu(nombre: String, icono: Int, arrow: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 10.dp)
    ) {
        Image(
            painter = painterResource(icono),
            contentDescription = nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Icon(
            painter = painterResource(R.drawable.arrow_card),
            contentDescription = "Arrow",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}