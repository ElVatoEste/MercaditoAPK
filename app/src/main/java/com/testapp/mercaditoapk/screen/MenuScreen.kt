package com.testapp.mercaditoapk.screen

import PublicationViewModel
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
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
    imageViewModel: ImageViewModel = viewModel(),
    publicationViewModel: PublicationViewModel = viewModel()
) {
    // Observe the recent publications ID LiveData
    val publicationsId = publicationViewModel.recentPublicationsId.observeAsState()
    val isLoading = publicationViewModel.loading.observeAsState(initial = true)
    val imageIds = imageViewModel.imageIDs.observeAsState()
    val imageLoading = imageViewModel.loading.observeAsState(initial = true)

    Log.d("publicationsId", "${publicationsId.value}")

    LaunchedEffect(publicationViewModel) {
        publicationViewModel.getRecentPublicationsId()
    }

    // Ensure the images are only downloaded once
    LaunchedEffect(publicationsId.value) {
        publicationsId.value?.let { ids ->
            if (ids.isNotEmpty()) {
                imageViewModel.getImagesForPublications(ids)
            }
        }
    }

    // Extract the value from State<Bitmap?> to Bitmap?
    val imagesState = imageViewModel.publicationImage.observeAsState()
    val images = imagesState.value?.let { listOf(it) } ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (isLoading.value == true || imageLoading.value == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Section(title = "Nuestros destacados", images = images)
            Section(title = "Sugerencias de hoy", images = images)
            Section(title = "Novedades de tus seguidos", images = images)
            Section(title = "Añadidos recientemente", images = images)
        }
    }
}

@Composable
fun Section(title: String, images: List<Bitmap>) {
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
            Text(text = "Ver todo", color = androidx.compose.ui.graphics.Color.Blue)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images) { image ->
                val painter = rememberImagePainter(data = image)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)  // aquí se cambia el tamaño de las imágenes
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
