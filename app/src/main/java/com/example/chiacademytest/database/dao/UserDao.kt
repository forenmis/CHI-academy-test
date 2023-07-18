package com.example.chiacademytest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chiacademytest.database.entity.UserDB

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(userDB: UserDB)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserDB>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserDB

    @Query("UPDATE users SET isStudent = :isStudent WHERE id = :id")
    suspend fun changeIsStudent(id: Long, isStudent: Boolean)

}