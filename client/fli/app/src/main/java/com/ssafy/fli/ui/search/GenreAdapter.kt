package com.ssafy.fli.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fli.databinding.ItemGenreBinding
import com.ssafy.fli.model.Genre

class GenreAdapter(private val context: Context) :
    ListAdapter<Genre, GenreAdapter.GenreViewHolder>(GenreDiffCallback()) {
    private lateinit var binding: ItemGenreBinding

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genresTv.text = genre.genreTitle
            binding.root.setOnClickListener {
                genreMusicListener.onClick(genre.genreId)
            }
            binding.genreLayout.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    genre.genreColor
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface GenreMusicListener {
        fun onClick(genreId: Int)
    }

    lateinit var genreMusicListener: GenreMusicListener
}

class GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return newItem.genreId == oldItem.genreId
    }
}

