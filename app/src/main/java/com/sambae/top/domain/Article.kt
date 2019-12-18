package com.sambae.top.domain

import retrofit2.http.Url
import java.util.*

data class Article(
    val title: String,
    val publishDate: Date,
    val abstract: String,
    val smallThumbUrl: Url,
    val largeThumbUrl: Url
)