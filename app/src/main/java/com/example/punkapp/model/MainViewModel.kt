package com.example.punkapp.model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data: PunkBean? by mutableStateOf<PunkBean?>(value = null)
    var detailBeerData: PunkBeanItem? by mutableStateOf<PunkBeanItem?>(value = null)
    var favoriteIcon by mutableStateOf(false)
    private var favoritesBeersList = mutableStateListOf<PunkBeanItem?>()
    val listOfBeers : List<PunkBeanItem?> = favoritesBeersList
    var errorMessage = mutableStateOf("")
    private var runInProgress = mutableStateOf(false)

    fun loadData() {
        data = null
        viewModelScope.launch(Dispatchers.Default) {
            try {
                data = PunkAPI.loadBeers()
                if(data == null){
                    throw Exception("No results")
                }
                errorMessage.value = ""
            }
            catch (e:Exception){
                errorMessage.value = "Error:  ${e.message}"
            }
            runInProgress.value = false
        }
    }

    fun loadDetailBeerData(navHostController: NavHostController, detailData: PunkBeanItem?) {
        detailBeerData = detailData
        navHostController.navigate(Routes.DetailScreen.route)
    }

    fun addToFavorites(data: PunkBeanItem?){
        favoritesBeersList.add(data)
        favoriteIcon = !favoriteIcon
    }

    fun removeFromFavorites(data: PunkBeanItem?){
        favoritesBeersList.remove(data)
        favoriteIcon = !favoriteIcon
    }

}