package com.example.bibliapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentSelectedScreen by navController.currentScreenAsState()
    val currentRoute by navController.currentRouteAsState()

    val bottomNavRoutes = listOf(
        LeafScreen.BibleSelect.route,
        LeafScreen.BookSelect.routeWithArguments,
        LeafScreen.ChapterSelect.routeWithArguments,
        LeafScreen.FavoriteChapters.route,
    )

    Scaffold(
        bottomBar = {
            if (currentRoute == null || bottomNavRoutes.contains(currentRoute)) {
                BottomNavBar(navController = navController, currentSelectedScreen = currentSelectedScreen)
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    currentSelectedScreen: RootScreen
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Select,
            onClick = { navController.navigateToRootScreen(RootScreen.Select) },
            alwaysShowLabel = true,
            label = {
                Text(text = "Select")
            },
            icon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Favorites,
            onClick = { navController.navigateToRootScreen(RootScreen.Favorites) },
            alwaysShowLabel = true,
            label = {
                Text(text = "Favorites")
            },
            icon = {
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = "Favorites"
                )
            }
        )
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RootScreen> {
    val selectedItem = remember { mutableStateOf<RootScreen>(RootScreen.Select) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RootScreen.Select.route } -> {
                    selectedItem.value = RootScreen.Select
                }
                destination.hierarchy.any { it.route == RootScreen.Favorites.route } -> {
                    selectedItem.value = RootScreen.Favorites
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<String?> {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.route
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

private fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}