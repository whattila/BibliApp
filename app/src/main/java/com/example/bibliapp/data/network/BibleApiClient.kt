package com.example.bibliapp.data.network

object BibleApiClient {
    val bibleApiService: BibleApiService by lazy {
        RetrofitClient.retrofit.create(BibleApiService::class.java)
    }
}