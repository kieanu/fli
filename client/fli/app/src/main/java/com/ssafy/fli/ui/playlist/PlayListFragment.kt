package com.ssafy.fli.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.FragmentPlayListBinding
import com.ssafy.fli.model.PlayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlayListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentPlayListBinding

    private val playListFragmentViewModel: PlayListFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlayListAdapter()
        setPlayListUi()
    }

    private fun setPlayListUi() {
        binding.addLayout.setOnClickListener {
            playListFragmentViewModel.addPlayList()
        }
    }

    private fun setPlayListAdapter() {
        val playListAdapter = PlayListAdapter(requireContext(), this, playListFragmentViewModel)
        playListAdapter.detailPlayListListener = object : PlayListAdapter.DetailPlayListListener {
            override fun onClick(playList: PlayList) {

            }
        }
        playListAdapter.deletePlayListListener = object : PlayListAdapter.DeletePlayListListener {
            override fun onClick(playListId: Int) {
                playListFragmentViewModel.deletePlayList(playListId)
                Constants.getSnackBar(binding.root, DELETE_PLAY_LIST_MESSAGE)
            }
        }

        binding.playListFragmentRecycler.adapter = playListAdapter

        playListFragmentViewModel.playListList.observe(viewLifecycleOwner) {
            playListAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        playListFragmentViewModel.getPlayListList()
    }

    companion object {

        private const val DELETE_PLAY_LIST_MESSAGE = "플레이 리스트가 삭제되었습니다."
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}