package com.warmerhammer.crowdsourceshoppingapp.itemview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.data.Comment

@Composable
fun CurrentComment(
    viewModel: MainActivityViewModel,
    comment: Comment,
    saveComment: () -> Unit
) {
    val currentCommentText = rememberSaveable { mutableStateOf("") }
    Column(
        Modifier
            .border(1.dp, Color.LightGray)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp, 5.dp, 5.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = currentCommentText.value,
                placeholder = {
                    Text(
                        text = "Enter comment here ...",
                        color = Color.LightGray,
                        fontStyle = FontStyle.Italic,
                    )
                },
                onValueChange = { str ->
                    currentCommentText.value = str
                },
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            TextButton(onClick = { viewModel.deleteComment(comment.itemId) }) {
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End
                )
            }
            TextButton(onClick = {
                viewModel.saveComment(currentCommentText.value, comment.commentId)
                saveComment()
            }) {
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}