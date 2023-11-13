package com.warmerhammer.crowdsourceshoppingapp.data

data class comment(
    var comment: String,
    val userId: Int,
    val itemId: Long = System.currentTimeMillis(),
    var isEditable : Boolean = false,
)
