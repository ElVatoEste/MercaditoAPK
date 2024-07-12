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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
        publicationViewModel.getPublicationById(publicationId - 1)
        imageViewModel.downloadPublicationImage(publicationId - 1)
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
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = publication.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
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
                        Text(
                            text = "C\$ ${publication.price}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Detalles de la publicación",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(text = "Estado: ${if (publication.isVisible) "Visible" else "No Visible"}")
                        Text(text = "Disponibilidad: ${publication.availabilityType}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Descripción",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(text = publication.description)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Observaciones",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(text = publication.observations)
                    }
                } ?: run {
                    Text(text = "No se encontraron detalles para esta publicación")
                }
            }
        }
    }
}
