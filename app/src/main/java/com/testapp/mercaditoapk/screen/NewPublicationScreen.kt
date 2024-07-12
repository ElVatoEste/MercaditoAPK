package com.testapp.mercaditoapk.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.R
import com.testapp.mercaditoapk.model.AvailabilityType
import com.testapp.mercaditoapk.model.Image
import com.testapp.mercaditoapk.model.PublicationDTO
import com.testapp.mercaditoapk.network.NetworkModule
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun CrearPublicacionScreen() {
    CrearPublicacionScreen(navController = rememberNavController())
}

@Composable
fun CrearPublicacionScreen(navController: NavController) {
    val studentCIF = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val categoryId = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val isFeatured = remember { mutableStateOf(false) }
    val availabilityType = remember { mutableStateOf(AvailabilityType.AVAILABLE) }
    val observations = remember { mutableStateOf("") }
    val isVisible = remember { mutableStateOf(true) }

    val titleError = remember { mutableStateOf(false) }
    val descriptionError = remember { mutableStateOf(false) }
    val priceError = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

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
            text = "Crear Publicación",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = studentCIF.value,
            onValueChange = { studentCIF.value = it },
            label = { Text("CIF del Estudiante") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Título") },
            isError = titleError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        if (titleError.value) {
            Text(
                text = "Este campo es obligatorio",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Descripción") },
            isError = descriptionError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        if (descriptionError.value) {
            Text(
                text = "Este campo es obligatorio",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = categoryId.value,
            onValueChange = { categoryId.value = it },
            label = { Text("ID de Categoría") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = price.value,
            onValueChange = { price.value = it },
            label = { Text("Precio") },
            isError = priceError.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        if (priceError.value) {
            Text(
                text = "Este campo es obligatorio",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = observations.value,
            onValueChange = { observations.value = it },
            label = { Text("Observaciones") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isFeatured.value,
                onCheckedChange = { isFeatured.value = it }
            )
            Text(
                text = "Destacado",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isVisible.value,
                onCheckedChange = { isVisible.value = it }
            )
            Text(
                text = "Visible",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val isValid = validateInputs(
                    title.value,
                    description.value,
                    price.value,
                    titleError,
                    descriptionError,
                    priceError
                )
                if (isValid) {
                    val publicationDTO = PublicationDTO(
                        id = 0L,
                        studentCIF = studentCIF.value.toLong(),
                        imageList = emptyList(), // Asignar la lista de imágenes según sea necesario
                        title = title.value,
                        description = description.value,
                        price = price.value.toDouble(),
                        isFeatured = isFeatured.value,
                        availabilityType = availabilityType.value,
                        observations = observations.value,
                        isVisible = isVisible.value
                    )
                    coroutineScope.launch {
                        val response = NetworkModule.publicacionesApi.createPublication(publicationDTO)
                        if (response.isSuccessful) {
                            navController.navigate("some_success_route")
                        } else {
                            // Manejar el error aquí
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Crear Publicación")
        }
    }
}

fun validateInputs(
    title: String,
    description: String,
    price: String,
    titleError: MutableState<Boolean>,
    descriptionError: MutableState<Boolean>,
    priceError: MutableState<Boolean>
): Boolean {
    titleError.value = title.isBlank()
    descriptionError.value = description.isBlank()
    priceError.value = price.isBlank()

    return !(titleError.value || descriptionError.value || priceError.value)
}
