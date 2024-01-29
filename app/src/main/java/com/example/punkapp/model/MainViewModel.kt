package com.example.punkapp.model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkapp.model.PunkBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data by mutableStateOf<PunkBean?>(null)

    fun loadData() {
        data = null
        viewModelScope.launch(Dispatchers.Default) {
            try {
                data = PunkAPI.loadActivity()
            }
            catch (e:Exception){
                println("MANNNN ERRRROR:  ${e.message}")
            }
        }
    }
}