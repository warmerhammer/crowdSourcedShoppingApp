package com.warmerhammer.crowdsourceshoppingapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ScreenDestination {
    val icon: ImageVector
    val route: String
}

object HomePage : ScreenDestination {
    override val icon = Icons.Filled.Home
    override val route = "homescreen"
}

object ItemView : ScreenDestination {
    override val icon = Icons.Filled.Home
    override val route = "itemview"
    const val itemId = "itemId"
    val routeWithArgs = "$route/{$itemId}"
    val arguments = listOf(
        navArgument(itemId) { type = NavType.StringType }
    )
}