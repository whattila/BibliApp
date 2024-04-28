package com.example.bibliapp.data.network

import com.example.bibliapp.data.network.models.Bible
import com.example.bibliapp.data.network.models.BibleSummary
import com.example.bibliapp.data.network.models.Book
import com.example.bibliapp.data.network.models.BookSummary
import com.example.bibliapp.data.network.models.Chapter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BibleApiService {
    // esetleg nem lehet ezt a headert kiszervezni valahova?
    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles")
    fun getBibles(): Call<List<BibleSummary>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}")
    fun getBible(@Path("bibleId") bibleId: String): Call<Bible>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}/books")
    fun getBooksOfBible(@Path("bibleId") bibleId: String): Call<List<BookSummary>>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    @GET("bibles/{bibleId}/books/{bookId}?include-chapters=true")
    fun getBook(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Call<Book>

    @Headers(
        value = [
            "accept: application/json",
            "api-key: 93c78c3cdbf7407bf4016ad0d81e4a56"
        ]
    )
    // TODO: ennek a kérésnek sokféle paramétert lehet adni! Vizsgáljuk meg ezeket, és döntsük el, hogy melyiket mire érdemes állítani!
    @GET("bibles/{bibleId}/chapters/{chapterId}")
    fun getChapter(@Path("bibleId") bibleId: String, @Path("chapterId") chapterId: String): Call<Chapter>
}