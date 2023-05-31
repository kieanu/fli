package com.ssafy.fli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.model.Favorite
import com.ssafy.fli.model.Music
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {

    private val _homeMusicList = MutableLiveData<List<Music>>()
    val homeMusicList: LiveData<List<Music>>
        get() = _homeMusicList

    init {
        getMusicList()
    }

    private fun getMusicList() {
        viewModelScope.launch {
            _homeMusicList.value = RetrofitUtil.musicService.getAllMusic()
        }
    }

    suspend fun likeUpdate(favorite: Favorite): Int {
        return RetrofitUtil.musicService.updateLike(favorite)
    }
}