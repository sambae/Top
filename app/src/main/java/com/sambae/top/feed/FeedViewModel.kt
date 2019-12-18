package com.sambae.top.feed

import androidx.lifecycle.*
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(private val category: Category, private val repo: Repository): ViewModel() {
    val articles: LiveData<List<Article>> = repo.articles

    init {
        viewModelScope.launch {
            getArticles()
        }
    }

    private suspend fun getArticles() {
        withContext(Dispatchers.IO) {
            repo.getArticlesFor(category)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val category: Category,
        private val repo: Repository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
                return FeedViewModel(category, repo) as T
            }
            throw IllegalArgumentException("Could not create view model")
        }
    }
}