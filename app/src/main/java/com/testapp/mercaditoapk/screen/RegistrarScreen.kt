package com.testapp.mercaditoapk.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.R

@Preview(showBackground = true)
@Composable
fun RegistrarScreenPreview() {
    RegistrarScreen(navController = rememberNavController())
}

@Composable
fun RegistrarScreen(navController: NavController) {
    val telefono = remember { mutableStateOf("") }
    val descripcion = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF153160),
                        colorResource(id = R.color.moradologo)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logoclean),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Crear una cuenta",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Agrega información adicional para impulsar tu perfil. Estos campos son opcionales y se pueden editar en cualquier momento en tus configuraciones.",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Icono de usuario
        Image(
            painter = painterResource(R.drawable.usericon),
            contentDescription = "User Icon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = telefono.value,
            onValueChange = { telefono.value = it },
            label = { Text("Número de teléfono (contacto adicional)") },
            modifier = Modifier.fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = descripcion.value,
            onValueChange = { descripcion.value = it },
            label = { Text("Ingresa una descripción") },
            modifier = Modifier.fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .weight(1f),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Al registrarte, aceptas los Términos de Servicio y Política de Privacidad",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle registration completion */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
        ) {
            Text(text = "Registrarse", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¿Ya tienes una cuenta? Inicia sesión",
            color = Color.White,
            fontSize = 16.sp,
            modifier = androidx.compose.ui.Modifier.clickable {
                navController.navigate("login")
            }
        )
    }
}
