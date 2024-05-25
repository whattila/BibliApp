package com.example.bibliapp.repository

import com.example.bibliapp.data.network.BibleApiService
import com.example.bibliapp.data.repositories.BrowseRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

class BrowseRepositoryTest {

    @MockK
    private val mockApi = mockk<BibleApiService>()

    @Test
    fun browseRepository_getAllBibles_exceptionButNotIOOrHttp(): Unit = runBlocking {
        coEvery { mockApi.getAllBibles() } throws NullPointerException()
        val repository = BrowseRepository(mockApi)
        assertThrows(Exception::class.java) {
            runBlocking {
                repository.getAllBibles()
            }
        }
    }
}