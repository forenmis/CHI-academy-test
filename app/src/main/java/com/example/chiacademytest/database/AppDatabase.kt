package com.example.chiacademytest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chiacademytest.database.dao.UserDao
import com.example.chiacademytest.database.entity.UserDB

@Database(
    entities = [UserDB::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}