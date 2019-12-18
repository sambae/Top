package com.sambae.top.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import java.util.*

@Entity(tableName = "article_table")
data class DatabaseArticle(
    @PrimaryKey var url: String = "",
    var title: String = "",
    var publishDate: Date = Date(),
    var abstract: String = "",
    var smallThumbUrl: String = "",
    var largeThumbUrl: String = "",
    var category: Category
) {
    fun toDomain(): Article {
        return Article(url, title, publishDate, abstract, smallThumbUrl, largeThumbUrl)
    }
}

