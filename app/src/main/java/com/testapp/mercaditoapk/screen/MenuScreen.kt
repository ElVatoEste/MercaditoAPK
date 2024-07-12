package com.testapp.mercaditoapk.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
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
    val studentImage = imageViewModel.studentImage.observeAsState().value

    LaunchedEffect(Unit) {
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

        studentImage?.let { responseBody ->
            val painter = rememberImagePainter(data = responseBody.byteStream())
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
