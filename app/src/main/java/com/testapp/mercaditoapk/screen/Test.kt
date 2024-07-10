package com.testapp.mercaditoapk.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.R
import com.testapp.mercaditoapk.model.StudentDTO
import com.testapp.mercaditoapk.viewmodel.StudentViewModel

@Composable
fun TestScreen(
    navController: NavController,
    studentViewModel: StudentViewModel = viewModel()
) {
    // Datos predefinidos
    val cif = 21011755L
    val nombres = "Juan"
    val apellidos = "Perez"
    val correo = "juan.perez@uamv.edu.ni"
    val contrasena = "password123"
    val telefono = "12345678"
    val descripcion = "Descripción de ejemplo"

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
        Text(
            text = "Enviar Datos",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Crear el objeto StudentDTO con los datos predefinidos
                val studentDTO = StudentDTO(
                    cif = cif,
                    name = nombres,
                    surname = apellidos,
                    email = correo,
                    password = contrasena,
                    phoneNumber = telefono,
                    personalDescription = descripcion
                )
                // Llamar al método para registrar al estudiante
                studentViewModel.createStudent(studentDTO)

                // Navegar a la pantalla de inicio de sesión
                navController.navigate("login")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
        ) {
            Text(text = "Enviar Datos", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnviarDatosScreenPreview() {
    TestScreen(navController = rememberNavController())
}
