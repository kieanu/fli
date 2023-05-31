package com.ssafy.fli.ui.playlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.ItemPlayListMusicBinding
import com.ssafy.fli.model.Music

class PlayListMusicAdapter(private val context: Context) :
    ListAdapter<Music, PlayListMusicAdapter.PlayListMusicViewHolder>(PlayListMusicDiffCallback()) {
    private lateinit var binding: ItemPlayListMusicBinding

    inner class PlayListMusicViewHolder(private val binding: ItemPlayListMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: Music) {
            binding.root.setOnClickListener {
                detailPlayListMusicListener.onClick(music.musicId)
            }
            binding.playListMusicTrail.setOnClickListener {
                deleteFavoriteListener.onClick(music.musicId)
            }

            binding.playListMusicTitle.text = music.musicTitle
            binding.playListMusicSinger.text = music.singer
            Constants.getImage(context, binding.playListMusicImg, music.realImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListMusicViewHolder {
        binding =
            ItemPlayListMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListMusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListMusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailPlayListMusicListener {
        fun onClick(musicId: Int)
    }

    lateinit var detailPlayListMusicListener: DetailPlayListMusicListener

    interface DeleteFavoriteListener {
        fun onClick(musicId: Int)
    }

    lateinit var deleteFavoriteListener: DeleteFavoriteListener

}

class PlayListMusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        Log.e("싸피areItemsTheSame", "$newItem    $oldItem")
        return oldItem.musicId == newItem.musicId
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return newItem == oldItem
    }
}