package com.warmerhammer.crowdsourceshoppingapp.accountscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.warmerhammer.crowdsourceshoppingapp.MainActivityViewModel
import com.warmerhammer.crowdsourceshoppingapp.R
import com.warmerhammer.crowdsourceshoppingapp.data.Account
import com.warmerhammer.crowdsourceshoppingapp.utils.ImagePicker

@Composable
fun AccountScreenPage(accountId: Int, mainActivityViewModel: MainActivityViewModel = viewModel()) {
    val account = accounts[accountId]
    val emailText = rememberSaveable { mutableStateOf(account.email) }
    val passwordText = rememberSaveable { mutableStateOf(account.password) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                ImagePicker()
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_star_outline_24),
                    modifier = Modifier
                        .size(50.dp),
                    contentDescription = null,
                    tint = Color.LightGray
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Master Shopper Points: ${account.points} / 100",
                        fontSize = 20.sp
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Column {
                    Text(
                        "Name",
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 3.dp, bottom = 7.dp)
                    )
                    Text(
                        text = account.name,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
                }

            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                Column {
                    Text(
                        text = "Email",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 2.dp, bottom = 7.dp)
                    )
                    TextField(
                        value = emailText.value,
                        onValueChange = { str -> emailText.value = str },
                    )
                }

            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                Column {
                    Text(
                        text = "Password",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 2.dp, bottom = 7.dp)
                    )
                    TextField(
                        value = passwordText.value,
                        onValueChange = { str -> passwordText.value = str },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }

            }
        )
    }
}

val accounts = listOf(
    Account(
        name = "John Doe",
        email = "johndoe@gmail.com",
        password = "password",
        id = 0,
        points = 0,
    ),
    Account(
        name = "Jane Doe",
        email = "janedoe@gmail.com",
        password = "password",
        id = 1,
        points = 100,
    )
)