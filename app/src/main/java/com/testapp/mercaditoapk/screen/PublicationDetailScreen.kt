package com.testapp.mercaditoapk.screen

import PublicationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.viewmodel.ImageViewModel
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun DetailScreen(
    navController: NavController,
    publicationId: Long,
    publicationViewModel: PublicationViewModel = viewModel(),
    imageViewModel: ImageViewModel = viewModel()
) {
    val publicationDetails = publicationViewModel.publication.observeAsState()
    val isLoading = publicationViewModel.loading.observeAsState(initial = true)
    val publicationImage = imageViewModel.publicationImage.observeAsState()

    LaunchedEffect(publicationId) {
        publicationViewModel.getPublicationById(publicationId)
        imageViewModel.downloadPublicationImage(publicationId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading.value || publicationImage.value == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            publicationDetails.value?.let { publication ->
                Text(text = publication.title)
                Spacer(modifier = Modifier.height(8.dp))
                publicationImage.value?.let { bitmap ->
                    val imageBitmap = bitmap.asImageBitmap()
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = publication.description)
                // Agrega más detalles de la publicación según sea necesario
            } ?: run {
                Text(text = "No se encontraron detalles para esta publicación")
            }
        }
    }
}
