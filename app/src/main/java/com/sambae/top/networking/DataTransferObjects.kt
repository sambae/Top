package com.sambae.top.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class NetworkArticle(
    @Json(name = "title") val title: String,
    @Json(name = "abstract") val abstract: String,
    @Json(name = "published_date") val published_date: Date
)

@JsonClass(generateAdapter = true)
data class NetworkResponse(
    @Json(name = "status") val status: String,
    @Json(name = "results") val articles: List<NetworkArticle>
)

