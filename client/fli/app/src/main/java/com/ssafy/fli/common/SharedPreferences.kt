package com.ssafy.fli.common

import android.content.Context
import android.content.SharedPreferences
import com.ssafy.fli.ApplicationClass.Companion.PRE_ID
import com.ssafy.fli.ApplicationClass.Companion.PRE_SNS
import com.ssafy.fli.ApplicationClass.Companion.TOKEN
import com.ssafy.fli.model.Id

class SharedPreferences(context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    //사용자 정보 저장
    fun addUser(id: Id) {
        val editor = preferences.edit()
        editor.putString(PRE_ID, id.id)
        editor.putInt(PRE_SNS, id.sns)
        editor.apply()
    }

    fun addToken(token: String) {
        val editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return preferences.getString(TOKEN, "").toString()
    }


    fun getUser(): Id {
        val id = preferences.getString(PRE_ID, "")
        return if (id != "") {
            val name = preferences.getInt(PRE_SNS, 0)
            Id(id.toString(), name)
        } else {
            Id()
        }
    }

    fun deleteUser() {
        //preference 지우기
        val editor = preferences.edit()
        editor.remove(PRE_ID)
        editor.remove(PRE_SNS)
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "smartstore_preference"
    }

}