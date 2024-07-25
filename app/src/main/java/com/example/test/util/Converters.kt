package com.example.test.util

import androidx.room.TypeConverter
import java.util.Arrays

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return if (value == null) null else ArrayList(
            Arrays.asList(
                *value.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()))
    }

    @TypeConverter
    fun toString(list: List<String?>?): String? {
        return if (list == null) null else java.lang.String.join(",", list)
    }
}