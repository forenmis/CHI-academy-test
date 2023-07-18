package com.example.chiacademytest.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.chiacademytest.entity.Image

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val favoriteImagesLD = MutableLiveData<List<Image>>(emptyList())

    fun addFavoriteImage(image: Image) {
        val currentFavorites = favoriteImagesLD.value ?: return
        favoriteImagesLD.postValue(currentFavorites + image)
    }
}