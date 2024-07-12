package com.testapp.mercaditoapk.screen

import PublicationViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.testapp.mercaditoapk.model.Publication


@Composable
fun DetailScreen(
    navController: NavController,
    publicationId: Long,
    publicationViewModel: PublicationViewModel = viewModel()
) {


}
