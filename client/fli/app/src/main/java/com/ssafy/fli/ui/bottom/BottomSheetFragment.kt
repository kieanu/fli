package com.ssafy.fli.ui.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.common.Constants
import com.ssafy.fli.databinding.FragmentBottomSheetBinding
import com.ssafy.fli.model.PlayListContent


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomSheetFragment() : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentBottomSheetBinding
    val bottomSheetFragmentViewModel: BottomSheetFragmentViewModel by viewModels()

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
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        interceptScroll()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomSheetAdapter()

    }

    private fun setBottomSheetAdapter() {
        val bottomSheetAdapter = BottomPlayListAdapter()
        requireArguments().getInt(MUSIC_ID).let { musicId ->
            bottomSheetAdapter.addPlayListListener =
                object : BottomPlayListAdapter.AddPlayListListener {
                    override fun onClick(playListId: Int) {
                        bottomSheetFragmentViewModel.insertPlayList(
                            PlayListContent(
                                musicId,
                                playListId
                            )
                        )
                        this@BottomSheetFragment.dismiss()
                        Constants.getSnackBar(binding.root, ADD_PLAY_LIST_MESSAGE)
                    }
                }
        }
        binding.bottomRv.adapter = bottomSheetAdapter
        bottomSheetFragmentViewModel.playListList.observe(viewLifecycleOwner) {
            bottomSheetAdapter.submitList(it)
        }
    }

    private fun interceptScroll() {
        // Intercept touch events on the RecyclerView
        binding.bottomTv.setOnTouchListener(OnTouchListener { _, event -> // Manually handle scrolling based on touch events
            binding.bottomParent.requestDisallowInterceptTouchEvent(true)
            true
        })
    }

    override fun onResume() {
        super.onResume()
        bottomSheetFragmentViewModel.getPlayListList()
    }

    companion object {

        private const val ADD_PLAY_LIST_MESSAGE = "플레이리스트에 추가 되었습니다."

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomSheetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}