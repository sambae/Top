package com.sambae.top.detail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.sambae.top.R
import com.sambae.top.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        val article = DetailFragmentArgs.fromBundle(requireNotNull(arguments)).article

        binding.article = article

        Picasso.get()
            .load(article.largeThumbUrl)
            .fit()
            .centerCrop()
            .into(binding.thumbnail)

        (activity as AppCompatActivity).supportActionBar?.title = article.title

        return binding.root
    }
}
