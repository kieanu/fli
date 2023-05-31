package com.ssafy.fli.model

data class PlayList(
    val playlistId: Int,
    val userId: String,
    val userSns: Int,
    var playListMusic: List<Music>
) {
    constructor(playlistId: Int, userId: String, userSns: Int) : this(playlistId, userId, userSns, emptyList())
}
