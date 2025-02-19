package com.example.presupuestosdisa.ui.view.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.utils.navegarPlayStore

@Composable
fun ComponenteVersionControl(
    fireBaseViewModel: FireBaseViewModel = hiltViewModel()
) {

    val blockVersion by fireBaseViewModel.blockVersion.collectAsState()
    val context = LocalContext.current

    if(blockVersion) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.fillMaxWidth().height(300.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.exclamation),
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(70.dp)
                    )
                    Spacer(modifier = Modifier.weight(0.3f))
                    Text(
                        text = "Versión no soportada",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(0.3f))
                    Text(
                        text = "Por favor, actualiza la aplicación",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.weight(0.7f))
                    Button(onClick = {
                        navegarPlayStore(context)
                    }, modifier = Modifier.padding(bottom = 30.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                        Text(text = "Actualizar")
                    }
                }
            }
        }
    }
}