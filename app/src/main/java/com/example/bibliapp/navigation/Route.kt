package com.example.bibliapp.navigation

sealed class RootScreen(val route: String) {
    object Select : RootScreen("select")
    object Favorites : RootScreen("favorites")
    object Read : RootScreen("read")
}

sealed class LeafScreen(val route: String) {
    object BibleSelect : LeafScreen("bibleSelect")
    object BookSelect : LeafScreen("bookSelect") {
        const val routeWithArguments: String = "bookSelect/{bibleId}"
        const val bibleId: String = "bibleId"
    }
    object ChapterSelect : LeafScreen("chapterSelect") {
        const val routeWithArguments: String = "chapterSelect/{bibleId}/{bookId}"
        const val bibleId: String = "bibleId"
        const val bookId: String = "bookId"
    }
    object FavoriteChapters : LeafScreen("favoriteChapters")
    object ReadChapter : LeafScreen("readChapter") {
        const val routeWithArguments: String = "readChapter?bibleId={bibleId}&chapterId={chapterId}&databaseChapterId={databaseChapterId}"
        const val bibleId: String = "bibleId"
        const val chapterId: String = "chapterId"
        const val databaseChapterId: String = "databaseChapterId"
    }
}