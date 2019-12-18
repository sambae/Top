package com.sambae.top

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sambae.top.domain.Category
import com.sambae.top.networking.Network
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
