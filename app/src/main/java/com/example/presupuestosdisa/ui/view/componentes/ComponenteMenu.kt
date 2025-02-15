package com.example.presupuestosdisa.ui.view.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.ui.theme.DisaPink

@Composable
fun ComponenteMenu(nombre: String, icono: Int, arrow: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp, 8.dp, 8.dp, 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .shadow(1.dp, shape = RoundedCornerShape(6.dp))
            .border(3.dp, Color.Black, RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .fillMaxHeight()
            .background(DisaPink),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icono),
            contentDescription = nombre,
            modifier = Modifier.size(90.dp).padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
        )
        Text(
            text = nombre,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 28.dp).weight(1f)
        )
        Image(
            painter = painterResource(arrow),
            contentDescription = "Arrow",
            modifier = Modifier.padding(end = 28.dp).size(35.dp)
        )
    }
}