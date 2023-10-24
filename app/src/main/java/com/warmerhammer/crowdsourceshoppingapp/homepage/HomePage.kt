package com.warmerhammer.crowdsourceshoppingapp.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.warmerhammer.crowdsourceshoppingapp.GroceryItem
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.R
import com.warmerhammer.crowdsourceshoppingapp.ui.components.ItemCard

@Composable
fun HomePage(
    mainActivityViewModel: MainActivityViewModel = viewModel(),
    onNavigate: (destination: String, id: String?) -> Unit,
) {
    mainActivityViewModel.setCurrentPage("homescreen")
    LazyColumn(
        Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(vertical = 5.dp, horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(groceryItems.size) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ItemCard(
                    name = groceryItems[index].name,
                    image = groceryItems[index].image,
                    price = groceryItems[index].price
                ) {
                    onNavigate("ItemView", "${groceryItems[index].id}")
                }
            }
        }
    }
}


val groceryItems = listOf<GroceryItem>(
    GroceryItem(
        name = "Banana",
        description = "A yellow fruit",
        price = 0.99,
        image = R.drawable.banana,
        category = "Fruit",
        id = 1
    ),
    GroceryItem(
        name = "Apple",
        description = "A red fruit",
        price = 2.99,
        image = R.drawable.apple,
        category = "Fruit",
        id = 2
    ),
    GroceryItem(
        name = "Ice Cream",
        description = "An orange fruit",
        price = 1.99,
        image = R.drawable.ice_cream,
        category = "Dessert",
        id = 3
    ),
    GroceryItem(
        name = "Milk",
        description = "A white drink",
        price = 3.99,
        image = R.drawable.milk,
        category = "Dairy",
        id = 4
    ),
)