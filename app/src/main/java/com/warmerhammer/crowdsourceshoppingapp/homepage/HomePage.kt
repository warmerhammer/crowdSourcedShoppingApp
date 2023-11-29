package com.warmerhammer.crowdsourceshoppingapp.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.fillMaxSize()) {
        val recompose = remember { mutableStateOf(0) }

        // filter bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp)
                .padding(start = 20.dp),
        ) {
            val filterModes = listOf("newest", "least votes", "most votes")
            var expanded by rememberSaveable { mutableStateOf(false) }
            var selectedMode by rememberSaveable { mutableStateOf("newest") }

            IconButton(onClick = { expanded = true }) {
                Row(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = null)
                    Text(
                        modifier = Modifier.padding(bottom = 3.dp),
                        text = selectedMode,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            DropdownMenu(
                modifier = Modifier.background(color = MaterialTheme.colors.background),
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                filterModes.forEach { mode ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.background)
                            .border(
                                width = 1.dp,
                                color = Color.White
                            ),
                        onClick = {
                            expanded = false
                            when (mode) {
                                "newest" -> mainActivityViewModel.sortByNewest()
                                "least votes" -> mainActivityViewModel.sortByLeastVotes()
                                "most votes" -> mainActivityViewModel.sortByMostVotes()
                            }
                            selectedMode = mode
                            recompose.value += 1
                        }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = mode,
                            textAlign = TextAlign.Center,
                            fontSize = 13.sp,
                        )
                    }
                }
            }
        }

        // Vertically scrolling list of individual posts
        key(recompose.value) {
            LazyColumn(
                Modifier
                    .background(color = MaterialTheme.colors.background)
                    .padding(vertical = 5.dp, horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(groceryItems.value.size) { index ->
                    val currentItem = groceryItems.value[index]
                    val numberOfUpVotes = remember { mutableStateOf(currentItem.upvotes) }
                    val numberOfComments =
                        mainActivityViewModel.comments.collectAsState().value.filter { comment ->
                            comment.itemId == currentItem.id
                        }.size
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ItemCard(
                            numberOfUpVotes = numberOfUpVotes,
                            numberOfComments = numberOfComments,
                            groceryItem = currentItem,
                            onNavigate = { onNavigate("ItemView", "${currentItem.id}") },
                            addItemClick = { mainActivityViewModel.addShoppingCartItem(currentItem) },
                            upvoteItem = {
                                mainActivityViewModel.upvoteItem(currentItem)
                                numberOfUpVotes.value += 1
                            },
                            downvoteItem = {
                                mainActivityViewModel.downvoteItem(currentItem)
                                numberOfUpVotes.value -= 1
                            },
                            onAddTag = { onNavigate("TagScreen", "${currentItem.id}") },
                        )
                    }

                }
            }
        }
    }

}