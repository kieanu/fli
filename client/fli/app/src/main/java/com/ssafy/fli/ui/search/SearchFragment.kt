package com.ssafy.fli.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.fli.ApplicationClass.Companion.GENRE_ID
import com.ssafy.fli.ApplicationClass.Companion.MUSIC_ID
import com.ssafy.fli.R
import com.ssafy.fli.databinding.FragmentSearchBinding
import com.ssafy.fli.model.Genre
import com.ssafy.fli.ui.BasicMusicAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSearchBinding
    private val list = arrayListOf<Genre>()
    private val searchFragmentViewModel: SearchFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        list.add(Genre(1, "Pop", R.color.red))
        list.add(Genre(2, "JAZZ", R.color.yellow))
        list.add(Genre(3, "ROCK", R.color.blue))
        list.add(Genre(4, "HIPHOP", R.color.green))
        list.add(Genre(5, "CLASSIC", R.color.purple_200))
        list.add(Genre(6, "BALLADE", R.color.purple_500))
        list.add(Genre(7, "ETC", R.color.purple_700))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGenreAdapter()
        setSearchUi()
    }

    private fun setSearchUi() = with(binding) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText == "") {
                    selectGenresTv.visibility = View.VISIBLE
                    genresRv.visibility = View.VISIBLE
                    musicRv.visibility = View.GONE
                } else {
                    selectGenresTv.visibility = View.GONE
                    genresRv.visibility = View.GONE
                    musicRv.visibility = View.VISIBLE
                    searchFragmentViewModel.searchMusic(newText)
                }
                return true
            }
        })
    }

    private fun setGenreAdapter() {
        val genreAdapter = GenreAdapter(requireContext())
        binding.genresRv.adapter = genreAdapter
        genreAdapter.genreMusicListener = object : GenreAdapter.GenreMusicListener {
            override fun onClick(genreId: Int) {
                findNavController().navigate(
                    R.id.action_searchFragment_to_musicFragment,
                    bundleOf(GENRE_ID to genreId)
                )
            }
        }
        genreAdapter.submitList(list)

        val searchMusicAdapter = BasicMusicAdapter(requireContext())
        binding.musicRv.adapter = searchMusicAdapter
        searchMusicAdapter.detailMusicListener = object : BasicMusicAdapter.DetailMusicListener {
            override fun onClick(musicId: Int) {
                findNavController().navigate(
                    R.id.action_search_to_musicDetailFragment,
                    bundleOf(MUSIC_ID to musicId)
                )
            }
        }
        searchFragmentViewModel.searchMusicList.observe(viewLifecycleOwner) {
            searchMusicAdapter.submitList(it)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}