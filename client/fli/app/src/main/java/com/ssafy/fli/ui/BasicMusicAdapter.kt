package com.ssafy.fli.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.ItemBasicMusicBinding
import com.ssafy.fli.model.Favorite
import com.ssafy.fli.model.Music

class BasicMusicAdapter(private val context: Context) :
    ListAdapter<Music, BasicMusicAdapter.BasicMusicViewHolder>(BasicMusicDiffCallback()) {
    private lateinit var binding: ItemBasicMusicBinding
    val user = ApplicationClass.sharedPreferencesUtil.getUser()

    inner class BasicMusicViewHolder(private val binding: ItemBasicMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: Music) {
            binding.root.setOnClickListener {
                detailMusicListener.onClick(music.musicId)
            }
            binding.root.setOnLongClickListener {
                insertFavoriteMusicListener.onClick(
                    Favorite(
                        0,
                        music.musicId,
                        user.id,
                        user.sns
                    )
                )
                true
            }
            binding.musicTitleTv.text = music.musicTitle
            Constants.getImage(context, binding.musicIv, music.realImg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicMusicViewHolder {
        binding = ItemBasicMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasicMusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasicMusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailMusicListener {
        fun onClick(musicId: Int)
    }

    lateinit var detailMusicListener: DetailMusicListener

    interface InsertFavoriteMusicListener {
        fun onClick(favorite: Favorite)
    }

    lateinit var insertFavoriteMusicListener: InsertFavoriteMusicListener

}

class BasicMusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return newItem.musicId == oldItem.musicId
    }
}