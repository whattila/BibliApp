package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.network.BibleApiService
import com.example.bibliapp.data.network.models.BibleDTO
import com.example.bibliapp.data.network.models.BookDTO
import com.example.bibliapp.data.network.models.createDomainBible
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.Book
import com.example.bibliapp.domain.Chapter
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import javax.inject.Inject

@ActivityScoped
class BrowseRepository @Inject constructor(
    private val bibleApiService: BibleApiService
) {
    suspend fun getAllBibles(): List<BibleSummary> {
        return try {
            val response = bibleApiService.getAllBibles().awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.data?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        }
    }

    suspend fun getBible(bibleId: String): Bible {
        return try {
            val bibleResponse = bibleApiService.getBible(bibleId).awaitResponse()
            val booksResponse = bibleApiService.getBooksOfBible(bibleId).awaitResponse()
            if (bibleResponse.isSuccessful && booksResponse.isSuccessful) {
                val bibleResponseBody = bibleResponse.body()
                val booksResponseBody = booksResponse.body()
                val bible = bibleResponseBody?.data ?: BibleDTO(id = bibleId)
                val books = booksResponseBody?.data ?: emptyList()
                createDomainBible(bible, books)
            }
            else {
                Bible()
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        }
    }

    suspend fun getBook(bibleId: String, bookId: String): Book {
        return try {
            val response = bibleApiService.getBook(bibleId, bookId).awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.data?.toDomain() ?: Book()
            }
            else {
                Book()
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        }
    }

    suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return try {
            val response = bibleApiService.getChapter(bibleId, chapterId).awaitResponse()
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.data?.toDomain() ?: Chapter()
            }
            else {
                Chapter()
            }
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        }
    }
}