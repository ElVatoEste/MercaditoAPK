package com.testapp.mercaditoapk.screen

import PublicationViewModel
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.testapp.mercaditoapk.viewmodel.ImageViewModel

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController(), cif = "21011754")
}
@Composable
fun MenuScreen(
    navController: NavController,
    cif: String,
    imageViewModel: ImageViewModel = viewModel()
) {
    val publicationViewModel = viewModel<PublicationViewModel>()
    val publicationsId = publicationViewModel.recentPublicationsId.observeAsState()
    val isLoading = publicationViewModel.loading.observeAsState(initial = true)
    val imageLoading = imageViewModel.loading.observeAsState(initial = true)

    LaunchedEffect(publicationViewModel) {
        publicationViewModel.getRecentPublicationsId()
    }

    LaunchedEffect(publicationsId.value) {
        publicationsId.value?.let { ids ->
            if (ids.isNotEmpty()) {
                imageViewModel.getImagesForPublications(ids)
            }
        }
    }

    val imagesState = imageViewModel.publicationImages.observeAsState()
    val images = imagesState.value ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (isLoading.value == true || imageLoading.value == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Section(title = "Nuestros destacados", images = images, navController = navController)
            Section(title = "Sugerencias de hoy", images = images, navController = navController)
            Section(title = "Novedades de tus seguidos", images = images, navController = navController)
            Section(title = "Añadidos recientemente", images = images, navController = navController)
        }
    }
}

@Composable
fun Section(title: String, images: List<Bitmap>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            Text(
                text = "Ver todo",
                color = androidx.compose.ui.graphics.Color.Blue,
                modifier = Modifier.clickable { /* Navegar a la pantalla completa */ }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images) { bitmap ->
                Image(
                    painter = rememberImagePainter(bitmap),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(5.dp)
                        .clickable {
                            // Navegar a la pantalla de detalle cuando se hace clic en la imagen
                            val publicationId = 123 // Reemplaza con la lógica para obtener el ID correcto
                            navController.navigate("detail_screen/${publicationId}")
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
