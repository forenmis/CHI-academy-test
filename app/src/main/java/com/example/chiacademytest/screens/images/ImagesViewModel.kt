package com.example.chiacademytest.screens.images

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiacademytest.app.App
import com.example.chiacademytest.entity.Image
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagesViewModel(application: Application) : AndroidViewModel(application) {

    private val networkManager by lazy { App.getInstance(application).networkManager }

    val imagesLD = MutableLiveData<List<Image>>()
    val exceptionLD = MutableLiveData<Throwable>()

    init {
        loadImages()
    }

    fun loadImages() =
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionLD.postValue(throwable)
        }) {
            val newList = networkManager.loadPosts(LIMIT)
            val oldList = imagesLD.value
            imagesLD.postValue(if (oldList != null) oldList + newList else newList)
        }

    companion object {
        private const val LIMIT = 10
    }
}
