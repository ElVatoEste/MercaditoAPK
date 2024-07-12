package com.testapp.mercaditoapk.screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberImagePainter
import com.testapp.mercaditoapk.viewmodel.ImageViewModel

@Composable
fun MenuScreen(
    navController: NavController,
    cif: String,
    imageViewModel: ImageViewModel = viewModel()
) {
    // Observe the student image LiveData
    val studentImage = imageViewModel.studentImage.observeAsState()

    // Ensure the image is only downloaded once
    LaunchedEffect(cif) {
        imageViewModel.downloadStudentImage(cif.toLong())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menú Principal")
        Text(text = "CIF: $cif")

        // Display the image if it's available
        studentImage.value?.let {
            val painter = rememberImagePainter(data = it)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(vertical = 16.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
