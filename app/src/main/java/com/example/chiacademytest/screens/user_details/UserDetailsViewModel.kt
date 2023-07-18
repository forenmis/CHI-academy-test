package com.example.chiacademytest.screens.user_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiacademytest.app.App
import com.example.chiacademytest.entity.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {

    val userLD = MutableLiveData<User>()
    val exceptionLD = MutableLiveData<Throwable>()

    private val databaseManager by lazy { App.getInstance(application).databaseManager }

    fun loadUser(id: Long) = viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
        exceptionLD.postValue(throwable)
    }) {
        val user = withContext(Dispatchers.IO) {
            databaseManager.getUserById(id)
        }
        userLD.postValue(user)
    }
}