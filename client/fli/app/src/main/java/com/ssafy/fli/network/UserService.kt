package com.ssafy.fli.network

import com.ssafy.fli.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("user/register")
    suspend fun login(@Body body: User): Boolean
}