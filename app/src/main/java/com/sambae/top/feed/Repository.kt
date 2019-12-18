package com.sambae.top.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sambae.top.database.ArticleDatabase
import com.sambae.top.domain.Article
import com.sambae.top.domain.Category
import com.sambae.top.networking.NetworkInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class Repository(
    private val database: ArticleDatabase,
    private val service: NetworkInterface,
    private val category: Category
) {
    var articles: LiveData<List<Article>> = Transformations.map(database.articleDao.getArticlesFor(category)) {
        it.map { entity -> entity.toDomain() }
    }

    suspend fun getArticlesFor(category: Category) {
        withContext(Dispatchers.IO) {
            try {

                val articles = service.getArticles(category).toEntity()
                database.articleDao.insert(articles)

            } catch(e: HttpException) {
                Log.e("Repository", e.toString())
            }
        }
    }
}