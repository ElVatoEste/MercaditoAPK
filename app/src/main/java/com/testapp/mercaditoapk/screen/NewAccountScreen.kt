package com.testapp.mercaditoapk.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.R

@Preview(showBackground = true)
@Composable
fun CrearCuentaScreenPreview() {
    CrearCuentaScreen(navController = rememberNavController())
}

@Composable
fun CrearCuentaScreen(navController: NavController) {
    val nombres = remember { mutableStateOf("") }
    val apellidos = remember { mutableStateOf("") }
    val cif = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val confirmarContrasena = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val nombresError = remember { mutableStateOf(false) }
    val apellidosError = remember { mutableStateOf(false) }
    val cifError = remember { mutableStateOf(false) }
    val correoError = remember { mutableStateOf(false) }
    val contrasenaError = remember { mutableStateOf(false) }
    val confirmarContrasenaError = remember { mutableStateOf(false) }

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
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = nombres.value,
                onValueChange = { nombres.value = it },
                label = { Text("Nombres") },
                isError = nombresError.value,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = apellidos.value,
                onValueChange = { apellidos.value = it },
                label = { Text("Apellidos") },
                isError = apellidosError.value,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cif.value,
            onValueChange = { cif.value = it },
            label = { Text("CIF") },
            isError = cifError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo.value,
            onValueChange = { correo.value = it },
            label = { Text("Correo electrónico institucional") },
            isError = correoError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contrasena.value,
            onValueChange = { contrasena.value = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = contrasenaError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.visibility)
                else
                    painterResource(id = R.drawable.visibility_off)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = confirmarContrasena.value,
            onValueChange = { confirmarContrasena.value = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = confirmarContrasenaError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                val image = if (confirmPasswordVisible)
                    painterResource(id = R.drawable.visibility)
                else
                    painterResource(id = R.drawable.visibility_off)

                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(painter = image, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val isValid = validateInputs(
                    nombres.value,
                    apellidos.value,
                    cif.value,
                    correo.value,
                    contrasena.value,
                    confirmarContrasena.value,
                    nombresError,
                    apellidosError,
                    cifError,
                    correoError,
                    contrasenaError,
                    confirmarContrasenaError
                )
                if (isValid) {
                    navController.navigate("registrar/${cif.value}/${nombres.value}/${apellidos.value}/${correo.value}/${contrasena.value}")
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
        ) {
            Text(text = "Siguiente", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¿Ya tienes una cuenta? Inicia sesión",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )
    }
}

fun validateInputs(
    nombres: String,
    apellidos: String,
    cif: String,
    correo: String,
    contrasena: String,
    confirmarContrasena: String,
    nombresError: MutableState<Boolean>,
    apellidosError: MutableState<Boolean>,
    cifError: MutableState<Boolean>,
    correoError: MutableState<Boolean>,
    contrasenaError: MutableState<Boolean>,
    confirmarContrasenaError: MutableState<Boolean>
): Boolean {
    var isValid = true

    nombresError.value = nombres.isBlank()
    apellidosError.value = apellidos.isBlank()
    cifError.value = cif.isBlank()
    correoError.value = correo.isBlank()
    contrasenaError.value = contrasena.isBlank()
    confirmarContrasenaError.value = confirmarContrasena.isBlank()

    if (contrasena != confirmarContrasena) {
        contrasenaError.value = true
        confirmarContrasenaError.value = true
        isValid = false
    }

    if (nombresError.value || apellidosError.value || cifError.value || correoError.value || contrasenaError.value || confirmarContrasenaError.value) {
        isValid = false
    }

    return isValid
}
