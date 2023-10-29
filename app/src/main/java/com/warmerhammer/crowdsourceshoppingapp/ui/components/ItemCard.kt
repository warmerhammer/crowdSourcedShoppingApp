package com.warmerhammer.crowdsourceshoppingapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warmerhammer.crowdsourceshoppingapp.R

@Composable
fun ItemCard(
    name: String,
    image: Int,
    price: Double,
    onNavigate: () -> Unit,
    addItemClick: () -> Unit,
) {
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
                    Text(text = name, color = MaterialTheme.colors.onBackground)
                    Text(
                        text = "Best Price: $$price",
                        color = Color(0xFF77a765),
                        textAlign = TextAlign.End,
                        fontSize = 11.sp
                    )
                }
                // image row start
                Row(
                    Modifier
                        .width(380.dp)
                        .height(150.dp)
                        .border(BorderStroke((.5).dp, Color.LightGray)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigate) {
                        Image(
                            modifier = Modifier.fillMaxHeight(),
                            painter = painterResource(id = image),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                        )
                    }

                }
                //bottom bar row start
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Outlined.KeyboardArrowUp,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                        Text(text = "0", color = MaterialTheme.colors.onBackground)
                        IconButton(onClick = { /*TODO*/ }) {
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
                                backgroundColor = Color(
                                    0xFF3d6036
                                )
                            )
                        ) {
                            Text(text = "Add to Cart", color = Color.White, fontSize = 11.sp)
                        }
                    }


                }
            }

        }
    )
}