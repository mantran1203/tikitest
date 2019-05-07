package com.man.myapplication.api

import com.man.myapplication.Constant.API_GET_KEYWORDS
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(API_GET_KEYWORDS)
    fun getKeywords(): Single<List<String>>


    companion object {

        private const val DEFAULT_URL = "https://raw.githubusercontent.com/"
        private val apiService: ApiService? = null

        fun getInstance(): ApiService {
            return apiService ?: create()
        }

        private fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(DEFAULT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}