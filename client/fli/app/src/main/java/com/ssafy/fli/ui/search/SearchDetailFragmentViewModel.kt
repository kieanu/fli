package com.ssafy.fli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.model.Music
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class SearchDetailFragmentViewModel : ViewModel() {
    private val _searchDetailMusicList = MutableLiveData<List<Music>>()
    val searchDetailMusicList: LiveData<List<Music>>
        get() = _searchDetailMusicList

    fun searchDetailAllMusic(genreId: Int) {
        viewModelScope.launch {
            _searchDetailMusicList.value = RetrofitUtil.musicService.getSearchDetailMusic(genreId)
        }
    }

}