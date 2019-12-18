package com.sambae.top.domain

import java.util.*

data class Article(
    val title: String,
    val publishDate: Date,
    val abstract: String,
    val smallThumbUrl: String,
    val largeThumbUrl: String
)