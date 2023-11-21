package com.warmerhammer.crowdsourceshoppingapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.warmerhammer.crowdsourceshoppingapp.mainscreen.MainScreen
import com.warmerhammer.crowdsourceshoppingapp.ui.theme.CrowdSourceShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainActivityViewModel()
        setContent {
            CrowdSourceShoppingAppTheme {
                App(viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun App(
    viewModel: MainActivityViewModel
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                androidx.compose.material3.Text("Navigation", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Home,
                            contentDescription = null
                        )
                    },
                    label = { androidx.compose.material3.Text(text = "Home") },
                    selected = false,
                    onClick = {
                        viewModel.setCurrentPage("homescreen")
                        navController.navigate("homescreen")
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_shopping_cart_24),
                            contentDescription = null
                        )
                    },
                    label = { androidx.compose.material3.Text(text = "Shopping Cart") },
                    selected = false,
                    onClick = {
                        viewModel.setCurrentPage("shoppingcartscreen")
                        navController.navigate("shoppingcartscreen")
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_account_circle_24),
                            contentDescription = null
                        )
                    },
                    label = { androidx.compose.material3.Text(text = "Account") },
                    selected = false,
                    onClick = {
                        viewModel.setCurrentPage("accountscreen")
                        navController.navigate(AccountScreen.route)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }) {

        MainScreen(
            viewModel = viewModel,
            navController = navController,
            drawerState = drawerState,
        )

    }

}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrowdSourceShoppingAppTheme {
        App(MainActivityViewModel())
    }
}