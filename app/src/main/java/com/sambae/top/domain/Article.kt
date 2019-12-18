package com.sambae.top.domain

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import kotlin.math.abs

@Parcelize
data class Article(
    val url: String,
    val title: String,
    val publishDate: LocalDateTime,
    val abstractText: String,
    val smallThumbUrl: String?,
    val largeThumbUrl: String?
): Parcelable
