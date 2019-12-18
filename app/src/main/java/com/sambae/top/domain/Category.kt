package com.sambae.top.domain

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Category: Parcelable {
    TECHNOLOGY, FOOD, TRAVEL;

    @SuppressLint("DefaultLocale")
    fun title(): String {
        return name.toLowerCase().capitalize()
    }

    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

