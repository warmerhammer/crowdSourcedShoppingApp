package com.warmerhammer.crowdsourceshoppingapp.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.ui.components.ItemCard

@Composable
fun HomePage(
    mainActivityViewModel: MainActivityViewModel = viewModel(),
    onNavigate: (destination: String, id: String?) -> Unit,
) {
    mainActivityViewModel.setCurrentPage("homescreen")
    val groceryItems = mainActivityViewModel.items.collectAsState()

    LazyColumn(
        Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(vertical = 5.dp, horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(groceryItems.value.size) { index ->
            val currentItem = groceryItems.value[index]
            val numberOfComments =
                mainActivityViewModel.comments.collectAsState().value.filter { comment ->
                    comment.itemId == currentItem.id
                }.size
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ItemCard(
                    numberOfComments = numberOfComments,
                    groceryItem = currentItem,
                    onNavigate = { onNavigate("ItemView", "${currentItem.id}") },
                    addItemClick = { mainActivityViewModel.addShoppingCartItem(currentItem) },
                    upvoteItem = { mainActivityViewModel.upvoteItem(currentItem) },
                    downvoteItem = { mainActivityViewModel.downvoteItem(currentItem) },
                    onAddTag = { onNavigate("TagScreen", "${currentItem.id}") },
                )
            }
        }
    }
}