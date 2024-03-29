package com.example.punkapp.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.punkapp.model.MainViewModel
import com.example.punkapp.model.Routes


@Composable

fun Homescreen (navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LaunchedEffect("") {
        viewModel.loadData()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(Routes.FavoritesScreen.route)
            }) {
                Icon(Icons.Default.Star, contentDescription = "Favorites")
            }
        }
    ) { innerPadding ->

        if(viewModel.errorMessage.value.isNotBlank()) {
            ErrorView(message = viewModel.errorMessage.value)
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(viewModel.data?.size ?: 0) { item ->

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                        .padding(8.dp) // Apply padding inside the border for the content
                        .clickable {
                            viewModel.loadDetailBeerData(
                                navHostController,
                                viewModel.data?.get(item)
                            )
                        },
                ) {
                    Column {
                        Text(
                            text = viewModel.data?.get(item)?.name ?: "",
                            modifier = Modifier.padding(6.dp),
                            color = Color.Blue,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = viewModel.data?.get(item)?.description ?: "",
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun ErrorView(modifier:Modifier =Modifier, message:String){
    Text(
        text = message,
        color= MaterialTheme.colorScheme.error,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp)
    )
}