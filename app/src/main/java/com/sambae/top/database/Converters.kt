package com.sambae.top.database

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.sambae.top.domain.Category
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

class CategoryConverters {
    @SuppressLint("DefaultLocale")
    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return category?.name?.toLowerCase()
    }

    @SuppressLint("DefaultLocale")
    @TypeConverter
    fun toCategory(value: String?): Category? {
        return value?.let { Category.valueOf(it.toUpperCase()) }
    }
}

