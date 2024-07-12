package com.testapp.mercaditoapk.screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.testapp.mercaditoapk.viewmodel.ImageViewModel
import okhttp3.ResponseBody


@Composable
fun MenuScreen(
    navController: NavController,
    cif: String,
    imageViewModel: ImageViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menú Principal")
        Text(text = "CIF: $cif")

        // Trigger downloading the image bytes
        imageViewModel.downloadStudentImage(cif.toLong())

        // Observe the student image LiveData
        val studentImage: Bitmap? = imageViewModel.studentImage.value

        // Display the image if it's available
        studentImage?.let {
            val painter = rememberImagePainter(data = it)
            Image(
                painter = painter,
                contentDescription = null, // Optional content description
                modifier = Modifier
                    .size(200.dp) // Adjust size as needed
                    .padding(vertical = 16.dp),
                contentScale = ContentScale.Crop // Adjust content scale as needed
            )
        }
    }
}
