package com.warmerhammer.crowdsourceshoppingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warmerhammer.crowdsourceshoppingapp.ItemView.arguments
import com.warmerhammer.crowdsourceshoppingapp.homepage.HomePage
import com.warmerhammer.crowdsourceshoppingapp.itemview.ItemViewPage
import com.warmerhammer.crowdsourceshoppingapp.ui.theme.CrowdSourceShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

@Composable
fun App(
    viewModel: MainActivityViewModel
) {
    val navController = rememberNavController()
    val currentPage = viewModel.currentpage.collectAsState()
    Log.i("MainActivity", "Current page 1 is ${currentPage.value}")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = MaterialTheme.colors.background,
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentPage.value != "homescreen") {
                            navController.navigate("homescreen")
                            viewModel.setCurrentPage("homescreen")
                        }
                    }) {
                        Icon(
                            if (currentPage.value == "homescreen") Icons.Outlined.Menu else Icons.Outlined.ArrowBack,
                            contentDescription = null
                        )
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
                    arguments = arguments
                ) { backStackEntry ->
                    ItemViewPage(
                        backStackEntry.arguments?.getString("itemId")!!,
                        viewModel
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
        App(MainActivityViewModel())
    }
}