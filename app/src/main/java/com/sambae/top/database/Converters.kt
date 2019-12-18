package com.sambae.top.database

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.sambae.top.domain.Category
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it),
                TimeZone.getDefault().toZoneId()
            )
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.atZone(TimeZone.getDefault().toZoneId())
            ?.toInstant()
            ?.toEpochMilli()
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

