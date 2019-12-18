package com.sambae.top.database

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "article_table")
data class DatabaseArticle(
    @PrimaryKey var url: String,
    var title: String,
    var publishDate: LocalDateTime,
    var abstractText: String,
    var smallThumbUrl: String?,
    var largeThumbUrl: String?,
    var category: Category
) {

    fun toDomain(): Article {
        return Article(url, title, publishDate, abstractText, smallThumbUrl, largeThumbUrl)
    }
}

