package com.sambae.top.shared

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("app:dateFormatted")
fun setDateFormatted(view: TextView, date: LocalDateTime) {
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    view.text = date.format(formatter)
}