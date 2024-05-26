package com.example.bibliapp.utilities

import com.example.bibliapp.data.network.models.BibleDTO
import com.example.bibliapp.data.network.models.BookSummaryDTO
import com.example.bibliapp.data.network.models.LanguageDTO
import com.example.bibliapp.data.network.models.createDomainBible
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.BookSummary
import com.example.bibliapp.domain.Language
import org.junit.Test
import org.junit.Assert.assertEquals

class CreateDomainBibleTest {
    private val bibleDTO = BibleDTO(
        id = "e4581313051f2861-01",
        name = "Biblica® Open Icelandic Contemporary New Testament and Psalms",
        nameLocal = "Biblica® Opna Nýja testamentið og Sálmarnir endursagðir á daglegu máli",
        abbreviation = "OLO",
        abbreviationLocal = "OLO",
        description = "Protestant NT and Psalms",
        descriptionLocal = "Protestant NT and Psalms",
        type = "text",
        language = LanguageDTO(id = "isl", name = "Icelandic", scriptDirection = "LTR")
    )

    private val bookSummaryDTOList = listOf(
        BookSummaryDTO(
            id = "MAT",
            name = "Matteus"
        ),
        BookSummaryDTO(
            id = "MRK",
            name = "Markús"
        )
    )

    private val bible = Bible(
        summary = BibleSummary(
            id = "e4581313051f2861-01",
            name = "Biblica® Open Icelandic Contemporary New Testament and Psalms",
            nameLocal = "Biblica® Opna Nýja testamentið og Sálmarnir endursagðir á daglegu máli",
            abbreviation = "OLO",
            abbreviationLocal = "OLO",
            description = "Protestant NT and Psalms",
            descriptionLocal = "Protestant NT and Psalms",
            language = Language(id = "isl", name = "Icelandic", scriptDirection = "LTR")
        ),
        books = listOf(
            BookSummary(
                id = "MAT",
                name = "Matteus"
            ),
            BookSummary(
                id = "MRK",
                name = "Markús"
            )
        )
    )

    @Test
    fun createDomainBible_happyPath() {
        val result = createDomainBible(bibleDTO, bookSummaryDTOList)
        assertEquals(bible, result)
    }

    @Test
    fun createDomainBible_someNullValues() {
        val bibleDTOWithNulls = bibleDTO.copy(description = null, language = bibleDTO.language.copy())
        val listWithNulls = listOf(
            BookSummaryDTO(
                id = "MAT",
                name = null
            ),
            BookSummaryDTO(
                id = "MRK",
                name = "Markús"
            )
        )
        val bibleFromNulls = bible.copy(
            summary = bible.summary.copy(
                description = "No description",
                language = bible.summary.language.copy()),
            books = listOf(
                BookSummary(
                    id = "MAT",
                    name = "No name"
                ),
                BookSummary(
                    id = "MRK",
                    name = "Markús"
                )
            ))

        val result = createDomainBible(bibleDTOWithNulls, listWithNulls)
        assertEquals(bibleFromNulls, result)
    }
}