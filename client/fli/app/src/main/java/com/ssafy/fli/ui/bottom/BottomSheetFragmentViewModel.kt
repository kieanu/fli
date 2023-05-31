package com.ssafy.fli.ui.bottom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.model.PlayList
import com.ssafy.fli.model.PlayListContent
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class BottomSheetFragmentViewModel : ViewModel() {

    private val _playListList = MutableLiveData<List<PlayList>>()
    val playListList: LiveData<List<PlayList>>
        get() = _playListList

    fun getPlayListList() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        viewModelScope.launch {
            _playListList.value = RetrofitUtil.playListService.getAllPlayList(
                userId = user.id,
                userSns = user.sns
            )
        }
    }

    fun insertPlayList(playListContent: PlayListContent) {
        viewModelScope.launch {
            RetrofitUtil.playListContentService.insertPlayListContent(playListContent)
        }
    }

}