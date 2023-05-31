package com.ssafy.fli.network

import com.ssafy.fli.ApplicationClass

class RetrofitUtil {
    companion object {
        val userService: UserService = ApplicationClass.retrofit.create(UserService::class.java)
        val musicService: MusicService = ApplicationClass.retrofit.create(MusicService::class.java)
        val playListService: PlayListService =
            ApplicationClass.retrofit.create(PlayListService::class.java)
        val playListContentService: PlayListContentService =
            ApplicationClass.retrofit.create(PlayListContentService::class.java)
        //val storeService = ApplicationClass.retrofit.create(FirebaseTokenService::class.java)
    }
}