package com.example.chiacademytest.screens.create_user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.chiacademytest.app.App
import com.example.chiacademytest.entity.User
import com.example.chiacademytest.utils.dateToString
import com.example.chiacademytest.utils.parseDate
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class CreateUserViewModel(application: Application) : AndroidViewModel(application) {
    private val nameLD = MutableLiveData<String>()
    private val databaseManager by lazy { App.getInstance(application).databaseManager }

    val exceptionLD = MutableLiveData<Throwable>()
    val savedLD = MutableLiveData<Boolean>()
    val dateBirthLD = MutableLiveData<String>()

    val isDataFilledLD = MediatorLiveData<Boolean>().apply {
        val listener: Observer<Any> = Observer {
            value = !dateBirthLD.value.isNullOrEmpty() && !nameLD.value.isNullOrEmpty()
        }
        addSource(dateBirthLD, listener)
        addSource(nameLD, listener)
    }


    fun changeDate(newDate: Long) {
        val date = Date(newDate)
        if (dateBirthLD.value != date.dateToString()) {
            dateBirthLD.postValue(date.dateToString())
        }
    }

    fun changeName(name: String) {
        nameLD.postValue(name)
    }

    fun createUser() = viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
        exceptionLD.postValue(throwable)
    }) {
        withContext(Dispatchers.IO) {
            val user = User(
                id = 0,
                name = nameLD.value ?: error("name is missing"),
                age = calculateAge(),
                isStudent = false
            )
            databaseManager.saveUser(user)
            joinAll()
        }
        savedLD.postValue(true)
    }

    private fun calculateAge(): Int {
        val strDateOfBirth = dateBirthLD.value ?: return 0
        val date = Calendar.getInstance().apply { time = strDateOfBirth.parseDate() }
        val now = Calendar.getInstance()
        val isAlreadyBirthday = date.get(Calendar.DAY_OF_YEAR) >= now.get(Calendar.DAY_OF_YEAR)
        return when {
            isAlreadyBirthday -> now.get(Calendar.YEAR) - date.get(Calendar.YEAR)
            else -> now.get(Calendar.YEAR) - date.get(Calendar.YEAR) - 1
        }
    }
}