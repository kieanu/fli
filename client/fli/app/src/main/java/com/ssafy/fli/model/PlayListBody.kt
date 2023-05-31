package com.ssafy.fli.model

data class PlayListBody(
    val playlistId: Int,
    val userId: String,
    val userSns: Int
) {
    constructor(userId: String, userSns: Int) : this(0, userId, userSns)
}