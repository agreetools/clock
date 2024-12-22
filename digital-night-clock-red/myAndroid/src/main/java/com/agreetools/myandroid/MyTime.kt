package com.agreetools.myandroid

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MyTime {

    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm") // Customize the format
        return currentTime.format(formatter)
    }
}
