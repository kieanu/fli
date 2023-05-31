package com.ssafy.fli.ui.musicdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.R
import com.ssafy.fli.common.Constants

import com.ssafy.fli.common.Constants.parseDate
import com.ssafy.fli.databinding.FragmentMusicDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MusicDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  var _binding: FragmentMusicDetailBinding? = null
    private val binding get() =_binding!!
    private val musicDetailFragmentViewModel: MusicDetailFragmentViewModel by viewModels()

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
        _binding = FragmentMusicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMusicDetailUI()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun setMusicDetailUI() = with(binding) {
        requireArguments().getInt(MUSIC_ID).let { musicId ->
            musicDetailFragmentViewModel.loadMusicDetail(musicId)

            musicDetailFragmentViewModel.musicDetail.observe(viewLifecycleOwner) {
                apply {
                    Constants.getImage(requireContext(), musicDetailIv, it.realImg)
                    musicDetailTitleTv.text = it.musicTitle
                    musicDetailDateTv.text = parseDate(it.musicDate.toString())
                    musicDetailSingerTv.text = it.singer
                    musicDetailLikeCountTv.text = it.musicLike.toString()
                    musicDetailRankTv.text = Constants.makeRank(it.musicRank)
                    musicDetailContentTv.text = it.musicDesc
                }
            }
            playlistAddBtn.setOnClickListener {
                findNavController().navigate(
                    R.id.action_musicDetailFragment_to_bottomSheetFragment,
                    bundleOf(MUSIC_ID to musicId)
                )
            }
        }
        binding.toolbarMusicDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MusicDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MusicDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}