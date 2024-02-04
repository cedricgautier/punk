package com.example.punkapp.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.punkapp.R
import com.example.punkapp.model.MainViewModel
import com.example.punkapp.model.PunkBeanItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable

fun DetailScreen(navHostController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val beer:PunkBeanItem? = viewModel.detailBeerData
    viewModel.favoriteIcon = beer in viewModel.listOfBeers

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(2.dp)){
        Text(text = beer?.name ?:"", fontSize = 30.sp,  modifier = Modifier.padding(6.dp), color = Color.Blue, textAlign = TextAlign.Center)
        Text(text = beer?.tagline ?:"",  modifier = Modifier.padding(6.dp), color = Color.Black, textAlign = TextAlign.Center)

        GlideImage(
            model = beer?.image_url,
            loading = placeholder(R.mipmap.ic_launcher_round),
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            contentDescription = viewModel.detailBeerData?.name,
            modifier = Modifier
                .padding(4.dp)
                .width(128.dp)
                .height(256.dp)
        )

        if (!viewModel.favoriteIcon){
            Button(
                onClick = { viewModel.addToFavorites(beer) },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = "Add to favorites", modifier = Modifier.padding(6.dp), textAlign = TextAlign.Center)
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        } else{
            Button(
                onClick = { viewModel.removeFromFavorites(beer) },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }

        Text(text = "Year brewed : ${beer?.first_brewed}" ?:"",  modifier = Modifier.padding(6.dp), color = Color.Black, textAlign = TextAlign.Justify)
        Text(text = beer?.description ?:"",  modifier = Modifier.padding(6.dp), color = Color.Black, textAlign = TextAlign.Justify)
        Text(text = "Tips : ${beer?.brewers_tips}" ?:"",  modifier = Modifier.padding(6.dp), color = Color.Black, textAlign = TextAlign.Justify)
    }
}