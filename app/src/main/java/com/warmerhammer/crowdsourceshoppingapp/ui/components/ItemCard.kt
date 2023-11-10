package com.warmerhammer.crowdsourceshoppingapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.warmerhammer.crowdsourceshoppingapp.R
import com.warmerhammer.crowdsourceshoppingapp.data.GroceryItem

@Composable
fun ItemCard(
    groceryItem: GroceryItem,
    onNavigate: () -> Unit,
    addItemClick: () -> Unit,
    upvoteItem: () -> Unit,
    downvoteItem: () -> Unit,
    onAddTag: () -> Unit
) {

    val upvotes = rememberSaveable { mutableStateOf(groceryItem.upvotes) }

    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        content = {
            Column(
                Modifier.padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // title bar row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = groceryItem.name, color = MaterialTheme.colors.onBackground)
                    Text(
                        text = "Best Price: $${groceryItem.price}",
                        color = Color(0xFF77a765),
                        textAlign = TextAlign.End,
                        fontSize = 11.sp
                    )
                }
                // image row start
                Row(
                    Modifier
                        .width(400.dp)
                        .height(200.dp)
                        .border(BorderStroke((.5).dp, Color.LightGray)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigate) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(groceryItem.image)
                                .build(),
                            modifier = Modifier.fillMaxHeight(),
                            contentDescription = groceryItem.description,
                            contentScale = ContentScale.FillHeight,
                        )
                    }
                }

                // tag row start
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row (
                        modifier = Modifier.width(260.dp)
                    ) {
                        Text(
                            text = "Tags: ",
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Start,
                            fontSize = 11.sp
                        )
                        LazyRow(
                            modifier = Modifier
                                .padding(start = 5.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            content = {
                                items(groceryItem.tags.size) { index ->
                                    Text(
                                        modifier =
                                        Modifier
                                            .background(
                                                shape = MaterialTheme.shapes.small,
                                                color = Color.LightGray,
                                            )
                                            .width(50.dp),
                                        text = groceryItem.tags[index],
                                        color = MaterialTheme.colors.onBackground,
                                        textAlign = TextAlign.Center,
                                        fontSize = 11.sp,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .offset(y = (-18).dp),
                        onClick = { onAddTag() }
                    ) {
                        Row (
                            modifier = Modifier.padding(end = 3.dp)
                        ){
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_add_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "Add Tag",
                                color = MaterialTheme.colors.secondary,
                                textAlign = TextAlign.End,
                                fontSize = 11.sp
                            )
                        }

                    }
                }

                //bottom bar row start
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = {
                            upvoteItem()
                            upvotes.value += 1
                        }) {
                            Icon(
                                Icons.Outlined.KeyboardArrowUp,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                        Text(
                            text = "${upvotes.value}",
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Start
                        )
                        IconButton(onClick = {
                            downvoteItem()
                            upvotes.value -= 1
                        }) {
                            Icon(
                                Icons.Outlined.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                    }

                    Row {
                        IconButton(onClick = onNavigate) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_outline_mode_comment_24),
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onBackground
                                )
                                Text(
                                    text = "0",
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { addItemClick() },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFFCC00)
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(text = "Add to Cart", color = Color.Black, fontSize = 11.sp)
                        }
                    }


                }
            }

        }
    )
}