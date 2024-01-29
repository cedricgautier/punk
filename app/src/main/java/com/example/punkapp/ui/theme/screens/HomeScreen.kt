package com.example.punkapp.ui.theme.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.punkapp.R
import com.example.punkapp.model.MainViewModel
import com.example.punkapp.model.PictureData


@Composable

fun Homescreen (navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
     LaunchedEffect(""){
     viewModel.loadData()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(viewModel.data?.size?:0) {
            Text(text = viewModel.data?.get(it)?.name ?:"")
        }
    }

}





@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureData, onPictureClick : () -> Unit = {}) {
    var isExpanded by remember { mutableStateOf(false) }

    var text = if (isExpanded) {
        data.longText
    } else (data.longText.take(20) + "...")

    Row(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .heightIn(max = 100.dp)
    ) {

        GlideImage(
            model = data.url,
            contentDescription = data.longText,
            loading = placeholder(R.mipmap.ic_launcher_round),
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max = 100.dp)
                .clickable(onClick = onPictureClick)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }) {
            Text(
                text = data.text,
                fontSize = 20.sp
            )
            Text(
                text = text,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.animateContentSize()
            )
        }
    }


}