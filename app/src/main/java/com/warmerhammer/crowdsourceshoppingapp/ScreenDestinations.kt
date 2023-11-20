package com.warmerhammer.crowdsourceshoppingapp

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ScreenDestination {
    val route: String
}

object HomePage : ScreenDestination {
    override val route = "homescreen"
}

object ItemView : ScreenDestination {
    override val route = "ItemView"
    const val itemId = "itemId"
    val routeWithArgs = "$route/{$itemId}"
    val arguments = listOf(
        navArgument(itemId) { type = NavType.StringType }
    )
}

object AccountScreen : ScreenDestination {
    override val route = "accountscreen"
}

object ShoppingCartScreen : ScreenDestination {
    override val route = "shoppingcartscreen"
}

object AddProductScreen : ScreenDestination {
    override val route = "addproductscreen"
}

object TagScreen : ScreenDestination {
    override val route = "TagScreen"
    const val itemId = "itemId"
    val routeWithArgs = "$route/{$itemId}"
    val arguments = listOf(
        navArgument(itemId) { type = NavType.StringType }
    )
}