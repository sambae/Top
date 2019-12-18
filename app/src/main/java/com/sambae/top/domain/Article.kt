package com.sambae.top.domain

import java.time.LocalDateTime

data class Article(
    val url: String,
    val title: String,
    val publishDate: LocalDateTime,
    val abstract: String,
    val smallThumbUrl: String,
    val largeThumbUrl: String
)