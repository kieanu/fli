package com.ssafy.fli.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.fli.ApplicationClass.Companion.GENRE_ID
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.R
import com.ssafy.fli.databinding.FragmentSearchDetailBinding
import com.ssafy.fli.ui.BasicMusicAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [SearchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchDetailFragment : Fragment() {
    lateinit var binding: FragmentSearchDetailBinding

    private val searchDetailFragmentViewModel: SearchDetailFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getInt(GENRE_ID).let { genreId ->
            searchDetailFragmentViewModel.searchDetailAllMusic(genreId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchDetailAdapter = BasicMusicAdapter(requireContext())
        searchDetailAdapter.detailMusicListener = object : BasicMusicAdapter.DetailMusicListener {
            override fun onClick(musicId: Int) {
                findNavController().navigate(
                    R.id.action_musicFragment_to_musicDetailFragment,
                    bundleOf(MUSIC_ID to musicId)
                )
            }
        }

        binding.searchDetailRv.adapter = searchDetailAdapter
        searchDetailFragmentViewModel.searchDetailMusicList.observe(viewLifecycleOwner) {
            searchDetailAdapter.submitList(it)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MusicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}