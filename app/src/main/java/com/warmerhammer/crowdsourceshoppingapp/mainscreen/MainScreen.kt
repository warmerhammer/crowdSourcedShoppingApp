package com.warmerhammer.crowdsourceshoppingapp.mainscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.warmerhammer.crowdsourceshoppingapp.AccountScreen
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.NavHost
import com.warmerhammer.crowdsourceshoppingapp.R
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
    drawerState: DrawerState,
) {

    val currentPage = viewModel.currentpage.collectAsState()
    val currentPoints = viewModel.currentPoints.collectAsState()
    val shoppingCartItems = viewModel.shoppingCartItems.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
                        } else {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }

                    }) {
                        Icon(
                            if (currentPage.value == "homescreen") Icons.Outlined.Menu else Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                actions = {

                    Column(
                        Modifier.padding(horizontal = 50.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_outline_star_outline_24),
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = "${currentPoints.value} / 100",
                            color = if (currentPoints.value >= 100) MaterialTheme.colors.onSurface else Color.LightGray,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("shoppingcartscreen")
                        viewModel.setCurrentPage("shoppingcartscreen")
                    }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_shopping_cart_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                            Text(
                                text = "items: ${shoppingCartItems.value.size}",
                                color = MaterialTheme.colors.secondary,
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
                    .padding(
                        PaddingValues(
                            bottom = 10.dp,
                            start = 20.dp,
                            top = 5.dp,
                            end = 20.dp
                        )
                    ),
                horizontalArrangement = Arrangement.spacedBy(105.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    IconButton(onClick = {
                        navController.navigate("homescreen")
                        viewModel.setCurrentPage("homescreen")
                    }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Outlined.Home,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary,
                            )
                            Text(
                                text = "Home",
                                color = MaterialTheme.colors.secondary,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                    IconButton(
                        onClick = {
                            navController.navigate("addproductscreen")
                            viewModel.setCurrentPage("addproductscreen")
                        }) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy((-2).dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Outlined.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier.size(27.dp)
                            )
                            Text(
                                text = "Add Item",
                                color = MaterialTheme.colors.secondary,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    IconButton(onClick = {
                        // TODO: change from dummy account selection
                        navController.navigate("${AccountScreen.route}/0")
                        viewModel.setCurrentPage("accountscreen")
                    }) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary,
                            )
                            Text(
                                text = "Account",
                                color = MaterialTheme.colors.secondary,
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
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(innerPadding)
        ) {
            NavHost(viewModel = viewModel, navController = navController)
        }

    }

}