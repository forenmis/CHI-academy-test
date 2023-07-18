package com.example.chiacademytest.screens.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chiacademytest.app.App
import com.example.chiacademytest.database.DatabaseManager
import com.example.chiacademytest.entity.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseManager: DatabaseManager

    val usersFlow = MutableStateFlow<List<User>>(emptyList())
    val exceptionFlow = MutableSharedFlow<Throwable>()

    init {
        databaseManager = App.getInstance(application).databaseManager
        loadUsers()

    }

    fun loadUsers() = viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
        exceptionFlow.tryEmit(throwable)
    }) {
        val listUsers = withContext(Dispatchers.IO) { databaseManager.getAllUsers() }
        usersFlow.emit(listUsers)
    }

    fun changeIsStudent(id: Long, isStudent: Boolean) =
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionFlow.tryEmit(throwable)
        }) { databaseManager.changeIsStudent(id, isStudent) }
}
