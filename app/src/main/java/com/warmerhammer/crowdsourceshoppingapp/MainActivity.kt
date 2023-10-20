package com.warmerhammer.crowdsourceshoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.warmerhammer.crowdsourceshoppingapp.ui.theme.CrowdSourceShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.composable
import com.warmerhammer.crowdsourceshoppingapp.ItemView.arguments
import com.warmerhammer.crowdsourceshoppingapp.homepage.HomePage
import com.warmerhammer.crowdsourceshoppingapp.itemview.ItemViewPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainActivityViewModel()
        setContent {
            CrowdSourceShoppingAppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = MaterialTheme.colors.background,
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_shopping_cart_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = "items: 0",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(PaddingValues(bottom = 10.dp, start = 20.dp, top = 5.dp, end = 20.dp)),
                horizontalArrangement = Arrangement.spacedBy(105.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Outlined.Home,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                            )
                            Text(
                                text = "Home",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy((-2).dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Outlined.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                                modifier = Modifier.size(27.dp)
                            )
                            Text(
                                text = "Scan",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                            )
                            Text(
                                text = "Account",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            // Homescreen(navController)
            NavHost(
                navController = navController,
                startDestination = HomePage.route,
                modifier = Modifier
            ) {
                composable(HomePage.route) {
                    HomePage { destinaton, id ->
                        when(destinaton) {
                            "ItemView" -> navController.navigate("${ItemView.route}/$id")
                        }
                    }
                }
                composable(route = ItemView.routeWithArgs, arguments = arguments) { backStackEntry ->
                    ItemViewPage(
                        backStackEntry.arguments?.getString("itemId")!!
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrowdSourceShoppingAppTheme {
        App()
    }
}