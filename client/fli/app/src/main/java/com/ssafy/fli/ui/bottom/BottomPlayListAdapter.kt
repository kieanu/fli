package com.ssafy.fli.ui.bottom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.common.Constants.convertPlayListMessage
import com.ssafy.fli.databinding.ItemBottomPlayListBinding
import com.ssafy.fli.model.PlayList

class BottomPlayListAdapter :
    ListAdapter<PlayList, BottomPlayListAdapter.BottomPlayListViewHolder>(BottomPlayListDiffCallback()) {
    private lateinit var binding: ItemBottomPlayListBinding

    inner class BottomPlayListViewHolder(private val binding: ItemBottomPlayListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playList: PlayList) {
            binding.root.setOnClickListener {
                addPlayListListener.onClick(playList.playlistId)
            }
            binding.bottomPlayListTitleTv.text = convertPlayListMessage(layoutPosition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomPlayListAdapter.BottomPlayListViewHolder {
        binding =
            ItemBottomPlayListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomPlayListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BottomPlayListViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    interface AddPlayListListener {
        fun onClick(playListId: Int)
    }

    lateinit var addPlayListListener: AddPlayListListener

}

class BottomPlayListDiffCallback : DiffUtil.ItemCallback<PlayList>() {
    override fun areItemsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return newItem.playlistId == oldItem.playlistId
    }
}