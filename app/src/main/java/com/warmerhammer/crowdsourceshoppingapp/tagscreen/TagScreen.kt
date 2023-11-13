package com.warmerhammer.crowdsourceshoppingapp.tagscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel

@Composable
fun TagScreen(
    viewModel: MainActivityViewModel,
    itemId: String,
) {
    val tags = viewModel.tagsPerItem.collectAsState()
    val newTagText = rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    viewModel.getTagsPerItem(itemId.toLong())
    viewModel.setCurrentPage("TagScreen")

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        // add new tag text field
        Row {
            Column {
                TextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = newTagText.value,
                    onValueChange = { str -> newTagText.value = str },
                    placeholder = {
                        Text(
                            text = "Add new tag...",
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colors.primary,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        textColor = MaterialTheme.colors.onBackground,
                    ),
                )
                Row {
                    TextButton(onClick = { newTagText.value = "" }) {
                        Text(text = "Cancel", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    TextButton(onClick = {
                        viewModel.addTagPerItem(
                            itemId.toLong(),
                            newTagText.value
                        )
                        newTagText.value = ""
                    }) {
                        Text(text = "Save", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

        }

        Row(modifier = Modifier.padding(horizontal = 15.dp)) {
            Text(
                "Tags:",
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontStyle = FontStyle(2),
                fontWeight = FontWeight.Bold,
            )
        }
        // list of tags
        Row(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(tags.value.size) { index ->
                    val currentTag = tags.value[index]
                    Text(
                        modifier =
                        Modifier
                            .background(
                                shape = MaterialTheme.shapes.small,
                                color = Color.LightGray,
                            )
                            .width(125.dp)
                            .padding(vertical = 5.dp),
                        text = currentTag,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }


}