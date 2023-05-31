package com.ssafy.fli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.model.Music
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class SearchFragmentViewModel : ViewModel() {
    private val _searchMusicList = MutableLiveData<List<Music>>()
    val searchMusicList: LiveData<List<Music>>
        get() = _searchMusicList

    fun searchMusic(keyword: String) {
        viewModelScope.launch {
            _searchMusicList.value = RetrofitUtil.musicService.getSearchMusic(keyword)
        }
    }

    fun searchAllMusic() {
        viewModelScope.launch {
            _searchMusicList.value = RetrofitUtil.musicService.getAllMusic()
        }
    }

}