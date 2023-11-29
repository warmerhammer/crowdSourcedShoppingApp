package com.warmerhammer.crowdsourceshoppingapp

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.warmerhammer.crowdsourceshoppingapp.mainscreen.MainScreen
import com.warmerhammer.crowdsourceshoppingapp.network.APIServices
import com.warmerhammer.crowdsourceshoppingapp.network.RetrofitService
import com.warmerhammer.crowdsourceshoppingapp.ui.theme.CrowdSourceShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        // Test Call
        val service = Retrofit.Builder()
            .baseUrl("https://shoppingapp-d56022f156a4.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(APIServices::class.java)
        CoroutineScope(IO).launch {
            val product = service.getProductID(id = 2)
            Log.d("MainActivity", "OnCreate: $product")
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
                        navController.navigate("${AccountScreen.route}/0")
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