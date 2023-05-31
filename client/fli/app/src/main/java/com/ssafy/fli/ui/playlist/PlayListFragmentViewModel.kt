package com.ssafy.fli.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.model.PlayList
import com.ssafy.fli.model.PlayListBody
import com.ssafy.fli.model.PlayListContent
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class PlayListFragmentViewModel : ViewModel() {
    private val user = ApplicationClass.sharedPreferencesUtil.getUser();

    private val _playListList = MutableLiveData<List<PlayList>>()
    val playListList: LiveData<List<PlayList>>
        get() = _playListList

    init {
        getPlayListList()
    }

    fun addPlayList() {
        viewModelScope.launch {
            RetrofitUtil.playListService.insertPlayList(PlayListBody(user.id, user.sns))
            getPlayListList()
        }

    }

    fun getPlayListList() {
        viewModelScope.launch {
            _playListList.value = RetrofitUtil.playListService.getAllPlayList(
                userId = user.id,
                userSns = user.sns
            )
            // 플레이리스트에 노래 채워넣기
            getPlayListMusicList()
        }
    }

    private fun getPlayListMusicList() {
//        val tempPlayList = playListList.value
//        viewModelScope.launch {
//            for (i in 0 until (tempPlayList?.size ?: 0)) {
//                tempPlayList!![i].playListMusic =
//                    RetrofitUtil.musicService.getAllPlayListMusic(tempPlayList[i].playlistId)
//                Log.e("싸피",tempPlayList[i].playListMusic.toString())
//            }
//        }
//        _playListList.value = tempPlayList ?: playListList.value
        viewModelScope.launch {
            _playListList.value?.forEach {
                it.playListMusic = RetrofitUtil.musicService.getAllPlayListMusic(it.playlistId)
                Log.e("싸피",it.playListMusic.toString())
            }
        }

    }

//    private fun getPlayListMusicListById(playListId: Int) {
//        val tempPlayList = playListList.value
//        viewModelScope.launch {
//            tempPlayList!![i].playListMusic =
//                RetrofitUtil.musicService.getAllPlayListMusic(tempPlayList[i].playlistId)
//        }
//        _playListList.value = tempPlayList ?: playListList.value
//    }

    fun deletePlayListMusic(playListContent: PlayListContent) {
        viewModelScope.launch {
            RetrofitUtil.playListContentService.deletePlayListContent(playListContent)//d오류 삭제되기전 update해서 이상하게 나오는듯?
        }
    }

    fun deletePlayList(playListId: Int) {
        viewModelScope.launch {
            RetrofitUtil.playListService.deletePlayList(playListId)
            getPlayListList()
        }
    }
}