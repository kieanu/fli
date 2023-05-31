package com.ssafy.fli.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.ApplicationClass.Companion.LIKE_DELETE_MESSAGE
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.ItemSimpleMusicBinding
import com.ssafy.fli.model.Favorite
import com.ssafy.fli.model.Music

class FavoriteMusicAdapter(private val context: Context) :
    ListAdapter<Music, FavoriteMusicAdapter.FavoriteMusicViewHolder>(FavoriteMusicDiffCallback()) {
    private lateinit var binding: ItemSimpleMusicBinding
    val user = ApplicationClass.sharedPreferencesUtil.getUser()

    inner class FavoriteMusicViewHolder(private val binding: ItemSimpleMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: Music) {
            binding.root.setOnClickListener {
                detailMusicListener.onClick(music.musicId)
            }
            binding.simpleDeleteIv.setOnClickListener {
                // val user = ApplicationClass.sharedPreferencesUtil.getUser() // 별로 안좋은듯
                updateFavoriteMusicListener.onClick(
                    Favorite(
                        0,
                        music.musicId,
                        user.id,
                        user.sns
                    )
                )
                Constants.getSnackBar(binding.root, LIKE_DELETE_MESSAGE)
            }
            binding.simpleTitleTv.text = music.musicTitle
            binding.simpleDescTv.text = music.musicDesc
            Constants.getImage(context, binding.simpleMusicIv, music.realImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMusicViewHolder {
        binding = ItemSimpleMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailMusicListener {
        fun onClick(musicId: Int)
    }

    lateinit var detailMusicListener: DetailMusicListener // 따로 만들기

    interface UpdateFavoriteMusicListener {
        fun onClick(favorite: Favorite)
    }

    lateinit var updateFavoriteMusicListener: UpdateFavoriteMusicListener
}

class FavoriteMusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return newItem.musicId == oldItem.musicId
    }
}