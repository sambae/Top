package com.sambae.top.feed

import androidx.lifecycle.*
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import com.sambae.top.networking.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles


    init {
        viewModelScope.launch {
            getArticles()
        }
    }

    // TODO: Move request to repository
    private suspend fun getArticles() {
        withContext(Dispatchers.IO) {
            val articles = Network.service.getArticlesForCategory(Category.FOOD).toDomain()
            withContext(Dispatchers.Main) {
                _articles.value = articles
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
                return FeedViewModel() as T
            }
            throw IllegalArgumentException("Could not create view model")
        }
    }
}