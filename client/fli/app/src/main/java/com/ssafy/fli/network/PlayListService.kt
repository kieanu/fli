package com.ssafy.fli.network

import com.ssafy.fli.model.PlayList
import com.ssafy.fli.model.PlayListBody
import retrofit2.http.*

interface PlayListService {
    @GET("playlist/{userId}/{userSns}")
    suspend fun getAllPlayList(
        @Path("userId") userId: String,
        @Path("userSns") userSns: Int
    ): List<PlayList>

    @POST("playlist/insert")
    suspend fun insertPlayList(@Body playlist: PlayListBody): Int

    @DELETE("playlist/delete/{playlistId}")
    suspend fun deletePlayList(@Path("playlistId") playListId: Int): Int
}