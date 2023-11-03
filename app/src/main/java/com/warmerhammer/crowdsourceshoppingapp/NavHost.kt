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

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavHost(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
) {
    // Homescreen(navController)
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
            )
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
    }

}