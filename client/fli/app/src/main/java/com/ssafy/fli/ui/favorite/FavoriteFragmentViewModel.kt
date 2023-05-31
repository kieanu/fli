package com.ssafy.fli.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.ApplicationClass.Companion.INDEX_FIRST
import com.ssafy.fli.ApplicationClass.Companion.RANK_ONE
import com.ssafy.fli.model.Favorite
import com.ssafy.fli.model.Music
import com.ssafy.fli.network.RetrofitUtil
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel : ViewModel() {
    private val _bestMusic = MutableLiveData<Music>()
    val bestMusic: LiveData<Music>
        get() = _bestMusic

    private val _favoriteMusicList = MutableLiveData<List<Music>>()
    val favoriteMusicList: LiveData<List<Music>>
        get() = _favoriteMusicList

    init {
        getBestMusic()
        getAllFavorite()
    }

    private fun getBestMusic() {
        viewModelScope.launch {
            _bestMusic.value = RetrofitUtil.musicService.getBest(RANK_ONE)[INDEX_FIRST]
        }
    }

    fun getAllFavorite() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        viewModelScope.launch {
            _favoriteMusicList.value =
                RetrofitUtil.musicService.getAllFavorite(user.id, user.sns)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            RetrofitUtil.musicService.updateLike(favorite)
            getAllFavorite()// 0 delete 1 insert
        }
    }
}