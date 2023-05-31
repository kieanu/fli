package com.ssafy.fli.model

data class Music(
    val genreId: Int,
    val musicDate: Long,
    val musicDesc: String,
    val musicId: Int,
    val musicImg: String,
    val musicLike: Int,
    val musicRank: Int,
    val musicTitle: String,
    val realImg: String,
    val singer: String
) {
    override fun toString(): String {
        return musicId.toString()
    }
}