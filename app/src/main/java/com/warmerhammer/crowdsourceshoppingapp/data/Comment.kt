package com.warmerhammer.crowdsourceshoppingapp.data

data class Comment(
    var comment: String,
    val userId: Int,
    val itemId: Long,
    val commentId: Long = System.currentTimeMillis(),
    var isEditable : Boolean = false,
)
