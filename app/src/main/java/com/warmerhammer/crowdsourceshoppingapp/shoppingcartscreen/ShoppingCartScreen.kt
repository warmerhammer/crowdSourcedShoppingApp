package com.warmerhammer.crowdsourceshoppingapp.shoppingcartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import java.text.DecimalFormat

@Composable
fun ShoppingCartPage(
    mainActivityViewModel: MainActivityViewModel = viewModel()
) {
    val shoppingCartItems = mainActivityViewModel.shoppingCartItems.collectAsState()
    Column {
        LazyColumn(
            Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            content = {
                items(shoppingCartItems.value.size) { index ->
                    val currentItem = shoppingCartItems.value[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .border((.5).dp, Color.LightGray)
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(50.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(currentItem.image).build(),
                            contentDescription = currentItem.description,
                            contentScale = ContentScale.Inside
                        )
                        Text(text = currentItem.name, fontSize = 12.sp)
                        Column (horizontalAlignment = Alignment.End) {
                            Row {
                                Text(
                                    text = "Best Price: ",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.End,
                                    color = Color(0xFF77a765)
                                )
                                Text(
                                    text = "$${currentItem.price}",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.End,
                                    color = Color(0xFF77a765),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row {
                                Text(
                                    text = "@ ${currentItem.store}",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.End,
                                    color = Color(0xFF77a765)
                                )
                            }
                        }

                    }
                }
            })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var total = 0.0
            shoppingCartItems.value.forEach { item ->
                total += item.price
            }
            val formattedTotal = DecimalFormat("#.##").format(total)

            Text(
                text = "Total",
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Start
            )

            Text(
                text = "$$formattedTotal",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.End
            )
        }
    }

}