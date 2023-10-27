package com.warmerhammer.crowdsourceshoppingapp.itemview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.R
import com.warmerhammer.crowdsourceshoppingapp.homepage.groceryItems
import com.warmerhammer.crowdsourceshoppingapp.ui.components.ItemCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemViewPage(
    GroceryItemID: String,
    mainActivityViewModel : MainActivityViewModel = viewModel()
) {

    val density = LocalDensity.current
    val groceryItem = groceryItems[GroceryItemID.toInt() - 1]
    mainActivityViewModel.setCurrentPage("itemview")

    BackdropScaffold(
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        appBar = { /*TODO*/ },
        backLayerContent = {
            ItemCard(
                name = groceryItem.name,
                image = groceryItem.image,
                price = groceryItem.price,
                onNavigate = { /*TODO*/ },
                addItemClick = { mainActivityViewModel.addShoppingCartItem(groceryItem) }
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
                        .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.Start) {
                    Text("Comments", fontSize = 11.sp, textAlign = TextAlign.Start, fontStyle = FontStyle(2))

                }
                LazyColumn(
                    state = rememberLazyListState(),
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
                            Text(text = comments[index], textAlign = TextAlign.Center)
                        }

                    }
                }
            }

        },
    ) {

    }
}

val comments = listOf(
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ornare massa eget egestas purus viverra accumsan in nisl. Morbi tristique senectus et netus et malesuada fames ac turpis. Tincidunt lobortis feugiat vivamus at augue eget. Rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar. Netus et malesuada fames ac turpis egestas sed. Tristique nulla aliquet enim tortor. Nisl nisi scelerisque eu ultrices vitae. Sed turpis tincidunt id aliquet risus feugiat. In fermentum et sollicitudin ac orci phasellus egestas tellus rutrum. Libero volutpat sed cras ornare arcu dui. In iaculis nunc sed augue lacus viverra vitae congue eu. Aenean sed adipiscing diam donec adipiscing tristique risus nec. Netus et malesuada fames ac turpis egestas. In ante metus dictum at. Ut placerat orci nulla pellentesque dignissim enim. Tincidunt vitae semper quis lectus nulla at volutpat diam ut.",
)