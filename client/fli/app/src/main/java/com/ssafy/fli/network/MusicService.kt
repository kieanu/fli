package com.ssafy.fli.network

import com.ssafy.fli.model.Favorite
import com.ssafy.fli.model.Music
import retrofit2.http.*

interface MusicService {

    @GET("music/all")
    suspend fun getAllMusic(): List<Music>

    @GET("music/{musicId}")
    suspend fun getMusic(@Path("musicId") musicId: Int): Music

    @GET("music/selectByRank/{ranking}")
    suspend fun getBest(@Path("ranking") ranking: Int): List<Music>

    @GET("music/favorite/{userId}/{userSns}")
    suspend fun getAllFavorite(
        @Path("userId") userId: String,
        @Path("userSns") userSns: Int
    ): List<Music>

    @GET("music/selectAllBySearch/{keyword}")
    suspend fun getSearchMusic(@Path("keyword") keyword: String): List<Music>

    @GET("music/genre/{genreId}")
    suspend fun getSearchDetailMusic(@Path("genreId") genreId: Int): List<Music>

    @GET("music/playlist/{playlistId}")
    suspend fun getAllPlayListMusic(@Path("playlistId") playlistId: Int): List<Music>

    @POST("music/updateLike")
    suspend fun updateLike(@Body favorite: Favorite): Int

}