package com.sambae.top.networking

import com.sambae.top.BuildConfig
import com.sambae.top.domain.Category
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

const val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"

interface NetworkInterface {
    @GET("{category}.json")
    suspend fun getArticlesForCategory(@Path("category") category: Category): NetworkResponse

}

object Network {
    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    private val httpClient = {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val originalUrl = request.url()

                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("api-key", BuildConfig.API_KEY)
                    .build()

                val newRequest = request.newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }.build()
    }()

    val service: NetworkInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(NetworkInterface::class.java)
}
