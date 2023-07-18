package com.example.chiacademytest.database

import android.content.Context
import androidx.room.Room
import com.example.chiacademytest.database.mapper.toUser
import com.example.chiacademytest.database.mapper.toUserDB
import com.example.chiacademytest.entity.User

class DatabaseManager(context: Context) {
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "users"
    ).build()

    suspend fun getAllUsers(): List<User> {
        return database.userDao().getAllUsers().map { it.toUser() }
    }

    suspend fun saveUser(user: User) {
        return database.userDao().saveUser(user.toUserDB())
    }

    suspend fun changeIsStudent(id: Long, isStudent: Boolean) {
        return database.userDao().changeIsStudent(id, isStudent)
    }

    suspend fun getUserById(id: Long): User {
        return database.userDao().getUserById(id).toUser()
    }
}