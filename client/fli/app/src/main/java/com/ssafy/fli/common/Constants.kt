package com.ssafy.fli.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ssafy.fli.R
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

object Constants {
    fun makeRank(rank: Int): String {
        return "$rank $RANK_TEXT"
    }

    fun getImage(context: Context, view: ImageView, url: String) {
        val decodedBytes: ByteArray = Base64.decode(url, Base64.DEFAULT)
        Glide.with(context)
            .load(decodedBytes)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }

    fun getSnackBar(view: View, text: String) {
        val snack = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        snack.setAction(SNACK_BAR_SUCCESS) {
            snack.dismiss()
        }
        snack.show()
    }

    fun parseDate(date: String): String {

        val year = date.substring(YEAR_START, MONTH_START)
        val month = date.substring(MONTH_START, DAY_START)
        val day = date.substring(DAY_START, DAY_END)

        return year + DATE_DIV + month + DATE_DIV + day
    }

    suspend fun loadImage(imageUrl: String): Bitmap? {
        val bmp: Bitmap? = null
        try {

            val url = URL(imageUrl)
            val stream = url.openStream()

            return BitmapFactory.decodeStream(stream)

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmp
    }


    fun convertPlayListMessage(position: Int) = PLAY_LIST_MESSAGE + position

    private const val RANK_TEXT = "등"
    private const val DATE_DIV = "-"
    private const val SNACK_BAR_SUCCESS = "확인"
    private const val YEAR_START = 0
    private const val MONTH_START = 4
    private const val DAY_START = 6
    private const val DAY_END = 8

    private const val PLAY_LIST_MESSAGE = "재생목록"

}