package com.sambae.top.feed

import androidx.lifecycle.*
import com.sambae.top.domain.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

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