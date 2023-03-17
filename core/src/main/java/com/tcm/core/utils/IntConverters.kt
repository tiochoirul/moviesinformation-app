package com.tcm.core.utils

import androidx.room.TypeConverter

object IntConverters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun toString(value: List<Int>): String {
        return value.joinToString(",")
    }
}