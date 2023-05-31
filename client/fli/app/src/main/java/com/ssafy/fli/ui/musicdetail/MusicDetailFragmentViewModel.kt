package com.ssafy.fli.ui.musicdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.model.Music
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class MusicDetailFragmentViewModel : ViewModel() {

    private val _musicDetail = MutableLiveData<Music>()
    val musicDetail: LiveData<Music>
        get() = _musicDetail

    fun loadMusicDetail(musicId: Int) {
        viewModelScope.launch {
            _musicDetail.value = RetrofitUtil.musicService.getMusic(musicId)
        }
    }

}