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
import androidx.compose.runtime.livedata.observeAsState
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
import com.testapp.mercaditoapk.viewmodel.StudentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen(navController: NavController, viewModel: StudentViewModel = viewModel()) {
    val cif = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginResult by viewModel.loginResult.observeAsState()

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
            text = "Bienvenido de nuevo",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = cif.value,
            onValueChange = { cif.value = it },
            label = { Text("CIF") },
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
        Spacer(modifier = Modifier.height(32.dp))

        loginResult?.let {
            it.fold(
                onSuccess = { student ->
                    navController.navigate("menu/${cif.value}")
                },
                onFailure = { error ->
                    Text(
                        text = error.message ?: "Login failed",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                viewModel.login(cif.value.toLong(), contrasena.value)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
        ) {
            Text(text = "Iniciar sesión", fontSize = 16.sp)
        }
        Text(
            text = "O",
            color = Color.White,
            fontSize = 16.sp
        )
        Button(
            onClick = { /* Handle Google login */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Logo",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Continuar con Google", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "¿No tienes una cuenta? Regístrate",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                navController.navigate("crear_cuenta")
            }
        )
    }
}
