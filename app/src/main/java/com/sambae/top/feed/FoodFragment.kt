package com.sambae.top.feed

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

import com.sambae.top.R
import com.sambae.top.databinding.FragmentFeedBinding
import com.sambae.top.domain.Category

class FoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onStart() {
        super.onStart()

        val action = FoodFragmentDirections.actionFoodFragmentToFeedFragment(Category.FOOD)
        findNavController().navigate(action)
    }
}

