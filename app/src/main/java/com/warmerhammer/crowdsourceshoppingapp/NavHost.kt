package com.warmerhammer.crowdsourceshoppingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.warmerhammer.crowdsourceshoppingapp.accountscreen.AccountScreenPage
import com.warmerhammer.crowdsourceshoppingapp.addproductscreen.addProductScreen
import com.warmerhammer.crowdsourceshoppingapp.homepage.HomePage
import com.warmerhammer.crowdsourceshoppingapp.itemview.ItemViewPage
import com.warmerhammer.crowdsourceshoppingapp.shoppingcartscreen.ShoppingCartPage
import com.warmerhammer.crowdsourceshoppingapp.tagscreen.TagScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavHost(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = HomePage.route,
        modifier = Modifier
    ) {
        composable(HomePage.route) {
            HomePage(
                onNavigate = { destinaton, id ->
                    when (destinaton) {
                        "ItemView" -> navController.navigate("${ItemView.route}/$id")
                        "TagScreen" -> navController.navigate("${TagScreen.route}/$id")
                    }
                },
                mainActivityViewModel = viewModel
            )
        }
        composable(
            route = ItemView.routeWithArgs,
            arguments = ItemView.arguments
        ) { backStackEntry ->
            ItemViewPage(
                backStackEntry.arguments?.getString("itemId")!!,
                viewModel
            ) { itemId ->
                // navigate to TagScreen
                navController.navigate("${TagScreen.route}/$itemId")
            }
        }
        composable(
            route = AccountScreen.routeWithArgs,
            arguments = AccountScreen.accountScreenArguments
        ) { backStackEntry ->
            AccountScreenPage(
                accountId = backStackEntry.arguments?.getInt(
                    "accountId"
                )!!,
                mainActivityViewModel = viewModel
            )
        }
        composable(ShoppingCartScreen.route) {
            ShoppingCartPage(
                mainActivityViewModel = viewModel
            )
        }
        composable(AddProductScreen.route) {
            addProductScreen {
                navController.navigate("homescreen")
                viewModel.setCurrentPage("homescreen")
            }
        }

        composable(
            route = TagScreen.routeWithArgs,
            arguments = ItemView.arguments
        ) { backStackEntry ->
            TagScreen(
                viewModel = viewModel,
                itemId = backStackEntry.arguments?.getString("itemId")!!
            )
        }
    }

}