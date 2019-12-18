package com.sambae.top.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.RoundedCornerTreatment
import com.sambae.top.database.ArticleDatabase
import com.sambae.top.databinding.ArticleListItemBinding
import com.sambae.top.databinding.FragmentFeedBinding
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import com.sambae.top.networking.Network
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.coroutines.withContext

class FeedFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = FeedFragmentArgs.fromBundle(requireNotNull(arguments))
        val binding = FragmentFeedBinding.inflate(inflater)

        val application = requireNotNull(activity).application
        val db = ArticleDatabase.getInstance(application)
        val repo = Repository(db, Network.service, args.category)

        val factory = FeedViewModel.Factory(args.category, repo)

        val viewModel = ViewModelProvider(this, factory)
            .get(FeedViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val listAdapter = ArticleListAdapter()

        val listView = binding.feedList.apply {
            adapter = listAdapter
        }

        viewModel.articles.observe(this, Observer {
            Log.i("FeedFragment", it.toString())
            listAdapter.submitList(it)
        })

        (activity as AppCompatActivity).supportActionBar?.title = args.category.title()

        return binding.root
    }
}

class ArticleListAdapter: ListAdapter<Article, ArticleListViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        return ArticleListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}

class ArticleListViewHolder(private val binding: ArticleListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.article = article

        binding.thumbnail.clipToOutline = true

        if (article.smallThumbUrl != null) {
            Picasso.get()
                .load(article.smallThumbUrl)
                .transform(
                    RoundedCornersTransformation(
                        12,
                        0,
                        RoundedCornersTransformation.CornerType.LEFT
                    )
                )
                .into(binding.thumbnail)
        } else {
            binding.thumbnail.setImageIcon(null)
        }


    }

    companion object {
        fun from(parent: ViewGroup): ArticleListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ArticleListItemBinding.inflate(inflater, parent, false)

            return ArticleListViewHolder(binding)
        }
    }
}
