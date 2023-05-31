package com.ssafy.fli

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ssafy.fli.common.SharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    companion object {
        const val SERVER_URL = "http://52.79.233.234:10000/fli/"
        const val PRE_ID = "id"
        const val PRE_SNS = "sns"
        const val TOKEN = "token"
        const val MUSIC_ID = "musicId"
        const val GENRE_ID = "genreId"
        const val LIKE_DELETE_MESSAGE = "좋아요 취소되었습니다."
        const val LIKE_ADD_MESSAGE = "좋아요 추가되었습니다."
        const val DELETE_PLAY_LIST_MUSIC_MESSAGE = "노래가 삭제되었습니다."

        const val RANK_ONE = 1
        const val INDEX_FIRST = 0

        lateinit var retrofit: Retrofit
        lateinit var sharedPreferencesUtil: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesUtil = SharedPreferences(applicationContext)
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
}