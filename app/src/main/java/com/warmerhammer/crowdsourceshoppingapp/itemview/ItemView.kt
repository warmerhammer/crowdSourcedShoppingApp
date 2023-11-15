package com.warmerhammer.crowdsourceshoppingapp.itemview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.R
import com.warmerhammer.crowdsourceshoppingapp.data.Comment
import com.warmerhammer.crowdsourceshoppingapp.ui.components.ItemCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemViewPage(
    groceryItemID: String,
    mainActivityViewModel: MainActivityViewModel = viewModel(),
    onAddTag: (itemId: String) -> Unit,
) {

    val groceryItem = mainActivityViewModel.getGroceryItem(groceryItemID.toLong())
    val comments = mainActivityViewModel.comments.collectAsState().value.filter {comment ->
        comment.itemId == groceryItemID.toLong()
    }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    mainActivityViewModel.setCurrentPage("itemview")

    BackdropScaffold(
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        appBar = { /*TODO*/ },
        backLayerContent = {
            ItemCard(
                numberOfComments = comments.size,
                groceryItem = groceryItem,
                onNavigate = { /*TODO*/ },
                addItemClick = { mainActivityViewModel.addShoppingCartItem(groceryItem) },
                upvoteItem = { mainActivityViewModel.upvoteItem(groceryItem) },
                downvoteItem = { mainActivityViewModel.downvoteItem(groceryItem) },
                onAddTag = { onAddTag(groceryItemID) },
            )
        },
        frontLayerContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.Black)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_drag_handle_24),
                        contentDescription = null
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Comments",
                        fontSize = 11.sp,
                        textAlign = TextAlign.Start,
                        fontStyle = FontStyle(2)
                    )

                }
                Row(Modifier.weight(2f)) {
                    LazyColumn(
                        state = scrollState,
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                    ) {
                        items(comments.size) { index ->
                            Row(
                                Modifier
                                    .border(1.dp, Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val isEditable =
                                    rememberSaveable { mutableStateOf(comments[index].isEditable) }
                                if (isEditable.value) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()
                                    ) {
                                        CurrentComment(
                                            viewModel = mainActivityViewModel,
                                            comment = comments[index]
                                        ) {
                                            isEditable.value = false

                                        }
                                    }
                                } else {
                                    Text(
                                        text = comments[index].comment,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }
                    }
                }
                Row(Modifier.weight(.25f)) {
                    IconButton(onClick = {
                        mainActivityViewModel.addBlankComment(
                            Comment(
                                comment = "",
                                userId = 0,
                                itemId = groceryItemID.toLong(),
                                isEditable = true
                            )
                        )
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(comments.size - 1)
                        }
                    }) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_reply),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                            Text(
                                text = "Reply",
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        },
    ) {

    }
}