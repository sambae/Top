package com.sambae.top.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sambae.top.database.ArticleDatabase
import com.sambae.top.databinding.FragmentFeedBinding
import com.sambae.top.domain.Category
import com.sambae.top.networking.Network

class FeedFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater)

        val application = requireNotNull(activity).application
        val db = ArticleDatabase.getInstance(application)
        val category = Category.FOOD
        val repo = Repository(db, Network.service, category)

        val factory = FeedViewModel.Factory(category, repo)

        val viewModel = ViewModelProvider(this, factory)
            .get(FeedViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}