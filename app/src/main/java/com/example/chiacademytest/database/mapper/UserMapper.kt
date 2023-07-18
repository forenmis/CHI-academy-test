package com.example.chiacademytest.database.mapper

import com.example.chiacademytest.database.entity.UserDB
import com.example.chiacademytest.entity.User

fun UserDB.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        age = this.age,
        isStudent = this.isStudent
    )
}

fun User.toUserDB(): UserDB {
    return UserDB(
        id = this.id,
        name = this.name,
        age = this.age,
        isStudent = this.isStudent
    )
}