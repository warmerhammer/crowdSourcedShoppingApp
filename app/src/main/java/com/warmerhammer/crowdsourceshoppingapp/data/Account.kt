package com.warmerhammer.crowdsourceshoppingapp.data

import com.warmerhammer.crowdsourceshoppingapp.R

data class Account(
    val name: String,
    val email: String,
    val password: String,
    val id: Int,
    val image: Int = R.drawable.ic_baseline_account_circle_24,
    var points: Int
)
