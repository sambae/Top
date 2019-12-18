package com.sambae.top.feed


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

import com.sambae.top.R
import com.sambae.top.domain.Category

class TechFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tech, container, false)
    }

    override fun onStart() {
        super.onStart()

        val action = TechFragmentDirections.actionTechFragmentToFeedFragment(Category.TECHNOLOGY)
        findNavController().navigate(action)
    }
}
