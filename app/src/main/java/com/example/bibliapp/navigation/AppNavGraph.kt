package com.example.bibliapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.bibliapp.feature.bible_select.BibleSelectScreen
import com.example.bibliapp.feature.book_select.BookSelectScreen
import com.example.bibliapp.feature.chapter_select.ChapterSelectScreen
import com.example.bibliapp.feature.favorite_chapters.FavoriteChaptersScreen
import com.example.bibliapp.feature.read_chapter.ReadChapterScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Select.route
    ) {
        addSelectRoute(navController)
        addFavoritesRoute(navController)
        addReadRoute()
    }
}

private fun NavGraphBuilder.addSelectRoute(navController: NavController) {
    navigation(
        route = RootScreen.Select.route,
        startDestination = LeafScreen.BibleSelect.route
    ) {
        showBibleSelect(navController)
        showBookSelect(navController)
        showChapterSelect(navController)
    }
}
private fun NavGraphBuilder.showBibleSelect(navController: NavController) {
    composable(route = LeafScreen.BibleSelect.route) {
        BibleSelectScreen(
            viewModel = hiltViewModel(),
            onBibleSelected = { navController.navigate("${LeafScreen.BookSelect.route}/$it") },
        )
    }
}
private fun NavGraphBuilder.showBookSelect(navController: NavController) {
    composable(
        route = LeafScreen.BookSelect.routeWithArguments,
        arguments = listOf(
            navArgument(LeafScreen.BookSelect.bibleId) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val bibleId = backStackEntry.arguments?.getString(LeafScreen.BookSelect.bibleId)

        BookSelectScreen(
            bibleId = bibleId!!,
            viewModel = hiltViewModel(),
            onBookSelected = { bId: String, bookId: String -> navController.navigate("${LeafScreen.ChapterSelect.route}/$bId/$bookId") },
        )
    }
}

private fun NavGraphBuilder.showChapterSelect(navController: NavController) {
    composable(
        route = LeafScreen.ChapterSelect.routeWithArguments,
        arguments = listOf(
            navArgument(LeafScreen.ChapterSelect.bibleId) { type = NavType.StringType },
            navArgument(LeafScreen.ChapterSelect.bookId) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val bibleId = backStackEntry.arguments?.getString(LeafScreen.ChapterSelect.bibleId)
        val bookId = backStackEntry.arguments?.getString(LeafScreen.ChapterSelect.bookId)

        ChapterSelectScreen(
            bibleId = bibleId!!,
            bookId = bookId!!,
            viewModel = hiltViewModel(),
            onChapterSelected = { bId: String, chapterId: String -> navController.navigate("${LeafScreen.ReadChapter.route}?${LeafScreen.ReadChapter.bibleId}=$bId&${LeafScreen.ReadChapter.chapterId}=$chapterId") },
        )
    }
}

private fun NavGraphBuilder.addFavoritesRoute(navController: NavController) {
    navigation(
        route = RootScreen.Favorites.route,
        startDestination = LeafScreen.FavoriteChapters.route
    ) {
        showFavoriteChapters(navController)
    }
}
private fun NavGraphBuilder.showFavoriteChapters(navController: NavController) {
    composable(route = LeafScreen.FavoriteChapters.route) {
        FavoriteChaptersScreen(
            viewModel = hiltViewModel(),
            onChapterSelected = { navController.navigate("${LeafScreen.ReadChapter.route}?${LeafScreen.ReadChapter.databaseChapterId}=$it") },
        )
    }
}

private fun NavGraphBuilder.addReadRoute() {
    navigation(
        route = RootScreen.Read.route,
        startDestination = LeafScreen.ReadChapter.routeWithArguments
    ) {
        showReadChapter()
    }
}
private fun NavGraphBuilder.showReadChapter() {
    composable(
        route = LeafScreen.ReadChapter.routeWithArguments,
        arguments = listOf(
            navArgument(LeafScreen.ReadChapter.bibleId) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(LeafScreen.ReadChapter.chapterId) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(LeafScreen.ReadChapter.databaseChapterId) {
                type = NavType.LongType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val bibleId = backStackEntry.arguments?.getString(LeafScreen.ReadChapter.bibleId)
        val chapterId = backStackEntry.arguments?.getString(LeafScreen.ReadChapter.chapterId)
        val databaseChapterId = backStackEntry.arguments?.getLong(LeafScreen.ReadChapter.databaseChapterId)

        ReadChapterScreen(bibleId = bibleId, chapterId = chapterId, databaseChapterId = databaseChapterId!!, viewModel = hiltViewModel())
    }
}