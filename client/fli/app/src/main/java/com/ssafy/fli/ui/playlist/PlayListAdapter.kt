package com.ssafy.fli.ui.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.ApplicationClass.Companion.DELETE_PLAY_LIST_MUSIC_MESSAGE
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.R
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.ItemPlayListBinding
import com.ssafy.fli.model.PlayList
import com.ssafy.fli.model.PlayListContent

class PlayListAdapter(
    private val context: Context,
    private val playListFragment: PlayListFragment,
    private val playListFragmentViewModel: PlayListFragmentViewModel
) :
    ListAdapter<PlayList, PlayListAdapter.PlayListViewHolder>(PlayListDiffCallback()) {
    private lateinit var binding: ItemPlayListBinding

    inner class PlayListViewHolder(
        private val binding: ItemPlayListBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playList: PlayList) {
            binding.playListTitle.text = context.getString(R.string.playlist_text, position + 1)
            binding.playListTrail.setOnClickListener {
                detailPlayListListener.onClick(playList)
                // 플레이리스트 음악목록 불러오기
                val adapter = PlayListMusicAdapter(context)
                binding.playListRecycler.adapter = adapter
                adapter.detailPlayListMusicListener =
                    object : PlayListMusicAdapter.DetailPlayListMusicListener {
                        override fun onClick(musicId: Int) {
                            findNavController(playListFragment).navigate(
                                R.id.action_playListFragment_to_musicDetailFragment,
                                bundleOf(MUSIC_ID to musicId)
                            )
                        }
                    }
                adapter.deleteFavoriteListener =
                    object : PlayListMusicAdapter.DeleteFavoriteListener {
                        override fun onClick(musicId: Int) {
                            playListFragmentViewModel.deletePlayListMusic(
                                PlayListContent(
                                    musicId,
                                    playList.playlistId
                                )
                            )
                            playListFragmentViewModel.getPlayListList()
                            adapter.submitList(playList.playListMusic.toMutableList())
                            Constants.getSnackBar(binding.root, DELETE_PLAY_LIST_MUSIC_MESSAGE)
                        }

                    }


                adapter.submitList(playList.playListMusic.toMutableList())

                if (binding.playListRecycler.visibility == View.VISIBLE) {
                    binding.playListTrail.setImageResource(R.drawable.outline_keyboard_arrow_down_24)
                    binding.playListRecycler.visibility = View.GONE
                } else {
                    binding.playListTrail.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                    binding.playListRecycler.visibility = View.VISIBLE
                }
            }

            binding.root.setOnLongClickListener {
                deletePlayListListener.onClick(playList.playlistId)
                true
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListAdapter.PlayListViewHolder {
        binding = ItemPlayListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailPlayListListener {
        fun onClick(playList: PlayList)
    }

    lateinit var detailPlayListListener: DetailPlayListListener

    interface DeletePlayListListener {
        fun onClick(playListId: Int)
    }

    lateinit var deletePlayListListener: DeletePlayListListener

}

class PlayListDiffCallback : DiffUtil.ItemCallback<PlayList>() {
    override fun areItemsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem.playlistId == newItem.playlistId
    }

    override fun areContentsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {

        return newItem == oldItem
    }
}