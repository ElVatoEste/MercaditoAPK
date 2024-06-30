package com.testapp.mercaditoapk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.testapp.mercaditoapk.viewmodel.StudentViewModel


class MainActivity : ComponentActivity() {
    private val studentViewModel by viewModels<StudentViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MercaditoApp()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MercaditoApp() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors()
                )
            }
        ) { innerPadding ->
            // Your main content goes here, use innerPadding for content padding
            Text(
                text = "Hello Mercadito UAM!",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}