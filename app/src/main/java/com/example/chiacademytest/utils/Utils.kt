package com.example.chiacademytest.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.dateToString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun String.parseDate(): Date {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.parse(this) ?: throw Exception()
}