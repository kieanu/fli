package com.ssafy.fli.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.ApplicationClass.Companion.LIKE_ADD_MESSAGE
import com.ssafy.fli.ApplicationClass.Companion.LIKE_DELETE_MESSAGE
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.R
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.FragmentHomeBinding
import com.ssafy.fli.model.Favorite
import com.ssafy.fli.ui.BasicMusicAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHomeAdapter()
        setHomeUi()

    }

    private fun setHomeUi() {
        binding.logOutBtn.setOnClickListener {
            ApplicationClass.sharedPreferencesUtil.deleteUser()
            Constants.getSnackBar(binding.root,"로그아웃 되었습니다.")
            findNavController().navigate(
                R.id.action_home_to_loginActivity,
            )

            activity?.finish()
        }
    }

    private fun setHomeAdapter() {
        val musicAdapter = BasicMusicAdapter(requireContext())
        musicAdapter.detailMusicListener = object : BasicMusicAdapter.DetailMusicListener {
            override fun onClick(musicId: Int) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_musicDetailFragment,
                    bundleOf(MUSIC_ID to musicId)
                )
            }
        }
        musicAdapter.insertFavoriteMusicListener =
            object : BasicMusicAdapter.InsertFavoriteMusicListener {
                override fun onClick(favorite: Favorite) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val result = async { homeFragmentViewModel.likeUpdate(favorite) }
                        if (result.await() < 0) {
                            CoroutineScope(Dispatchers.IO).launch {
                                Constants.getSnackBar(binding.root, LIKE_DELETE_MESSAGE)
                            }
                        } else {
                            CoroutineScope(Dispatchers.IO).launch {
                                Constants.getSnackBar(binding.root, LIKE_ADD_MESSAGE)
                            }
                        }
                    }
                }
            }
        binding.homeRv.adapter = musicAdapter
        homeFragmentViewModel.homeMusicList.observe(viewLifecycleOwner) {
            musicAdapter.submitList(it)
        }
    }

    companion object {


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}