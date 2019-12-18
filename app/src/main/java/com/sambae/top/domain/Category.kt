package com.sambae.top.domain

import android.annotation.SuppressLint

enum class Category {
    TECHNOLOGY, FOOD, TRAVEL;

    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

