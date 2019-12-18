package com.sambae.top.networking

import android.annotation.SuppressLint
import com.sambae.top.database.DatabaseArticle
import com.sambae.top.domain.Category
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class NetworkArticle(
    @Json(name = "short_url") val url: String,
    @Json(name = "title") val title: String,
    @Json(name = "abstract") val abstract: String,
    @Json(name = "published_date") val published_date: Date,
    @Json(name = "multimedia") val images: List<NetworkImage>
) {
    fun toEntity(category: Category): DatabaseArticle {
        val smallThumb = images.first { it.format == "Standard Thumbnail" }
        val largeThumb = images.first { it.format == "thumbLarge" }

        return DatabaseArticle(url, title, published_date, abstract, smallThumb.url, largeThumb.url, category)
    }
}

@JsonClass(generateAdapter = true)
data class NetworkResponse(
    @Json(name = "status") val status: String,
    @Json(name = "section") val category: String,
    @Json(name = "results") val articles: List<NetworkArticle>
) {
    @SuppressLint("DefaultLocale")
    fun toEntity(): List<DatabaseArticle> {
        val category = Category.valueOf(category.toUpperCase())
        return articles.map { it.toEntity(category) }
    }
}

@JsonClass(generateAdapter = true)
data class NetworkImage(
    @Json(name = "url") val url: String,
    @Json(name = "format") val format: String

)

