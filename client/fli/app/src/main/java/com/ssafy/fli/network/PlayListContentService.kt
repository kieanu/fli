package com.ssafy.fli.network

import com.ssafy.fli.model.PlayListContent
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST

interface PlayListContentService {
    @POST("playlistContent/insertMusic")
    suspend fun insertPlayListContent(@Body playListContent: PlayListContent): Int

    @HTTP(method = "DELETE", path = "playlistContent/deleteMusic", hasBody = true)
    suspend fun deletePlayListContent(@Body playlistContent: PlayListContent): Int
}