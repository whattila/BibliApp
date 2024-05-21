package com.example.bibliapp.data.network

import com.example.bibliapp.data.network.models.BibleDTO
import com.example.bibliapp.data.network.models.BookDTO
import com.example.bibliapp.data.network.models.BookSummaryDTO
import com.example.bibliapp.data.network.models.ChapterDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BibleApiService {
    // A header kiszervezéséről:
    // https://medium.com/@jeremy.leyvraz/kotlin-simplify-your-api-calls-with-elegance-with-retrofit-1be6da7adae4
    // https://square.github.io/okhttp/features/interceptors/
    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles")
    fun getAllBibles(): Call<ResponseBody<List<BibleDTO>>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}")
    fun getBible(@Path("bibleId") bibleId: String): Call<ResponseBody<BibleDTO>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}/books")
    fun getBooksOfBible(@Path("bibleId") bibleId: String): Call<ResponseBody<List<BookSummaryDTO>>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}/books/{bookId}?include-chapters=true")
    fun getBook(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Call<ResponseBody<BookDTO>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}/chapters/{chapterId}")
    fun getChapter(@Path("bibleId") bibleId: String, @Path("chapterId") chapterId: String): Call<ResponseBody<ChapterDTO>>
}