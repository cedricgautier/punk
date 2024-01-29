package com.example.punkapp.model

class PunkBean : ArrayList<PunkBeanItem>()

data class PictureData(val url: String, val text: String, val longText: String)

data class PunkBeanItem(
    val abv: Double,
    val attenuation_level: Double,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Int,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
)

