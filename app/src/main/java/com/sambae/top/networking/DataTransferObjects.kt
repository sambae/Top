package com.sambae.top.networking

import com.sambae.top.domain.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class NetworkArticle(
    @Json(name = "title") val title: String,
    @Json(name = "abstract") val abstract: String,
    @Json(name = "published_date") val published_date: Date,
    @Json(name = "multimedia") val images: List<NetworkImage>
) {
    fun toDomain(): Article {
        val smallThumb = images.first { it.format == "Standard Thumbnail" }
        val largeThumb = images.first { it.format == "thumbLarge" }

        return Article(title, published_date, abstract, smallThumb.url, largeThumb.url)
    }
}

@JsonClass(generateAdapter = true)
data class NetworkResponse(
    @Json(name = "status") val status: String,
    @Json(name = "results") val articles: List<NetworkArticle>
) {
    fun toDomain(): List<Article> {
        return articles.map { it.toDomain() }
    }
}

@JsonClass(generateAdapter = true)
data class NetworkImage(
    @Json(name = "url") val url: String,
    @Json(name = "format") val format: String
)

