package com.ssafy.fli.ui.favorite

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

import com.ssafy.fli.databinding.FragmentFavoriteBinding
import com.ssafy.fli.model.Favorite

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFavoriteBinding
    private val favoriteFragmentViewModel: FavoriteFragmentViewModel by viewModels()

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
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFavoriteAdapter()
        setFavoriteUi()
    }

    override fun onResume() {
        super.onResume()
        favoriteFragmentViewModel.getAllFavorite()
    }

    private fun setFavoriteAdapter() {
        val favoriteMusicAdapter = FavoriteMusicAdapter(requireContext())
        favoriteMusicAdapter.detailMusicListener =
            object : FavoriteMusicAdapter.DetailMusicListener {
                override fun onClick(musicId: Int) {
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_musicDetailFragment,
                        bundleOf(MUSIC_ID to musicId)
                    )
                }
            }
        favoriteMusicAdapter.updateFavoriteMusicListener =
            object : FavoriteMusicAdapter.UpdateFavoriteMusicListener {
                override fun onClick(favorite: Favorite) {
                    favoriteFragmentViewModel.deleteFavorite(favorite)
                }
            }
        binding.favoriteRv.adapter = favoriteMusicAdapter

        favoriteFragmentViewModel.favoriteMusicList.observe(viewLifecycleOwner) {
            favoriteMusicAdapter.submitList(it)
        }
    }

    private fun setFavoriteUi() = with(binding) {
        favoriteFragmentViewModel.bestMusic.observe(viewLifecycleOwner) {
            Constants.getImage(requireContext(), mostFavoriteIv, it.realImg)
            mostFavoriteTitleTv.text = it.musicTitle
            mostFavoriteDescIv.text = it.musicDesc
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}