package com.example.punkapp.model

sealed class Routes (val route: String){
    object HomeScreen : Routes("homeScreen")
    object DetailScreen : Routes("detailScreen")
    object FavoritesScreen : Routes("favoritesScreen")

}