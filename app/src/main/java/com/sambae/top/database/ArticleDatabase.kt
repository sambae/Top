package com.sambae.top.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.sambae.top.domain.Category

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table WHERE category=:category ORDER BY publishDate DESC")
    fun getArticlesFor(category: Category): LiveData<List<DatabaseArticle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(articles: List<DatabaseArticle>)
}

@Database(entities = [DatabaseArticle::class], version = 3, exportSchema = false)
@TypeConverters(DateConverters::class, CategoryConverters::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance != null) {
                    return instance
                }

                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_database"
                ).fallbackToDestructiveMigration()
                .build()

                return instance
            }
        }
    }
}