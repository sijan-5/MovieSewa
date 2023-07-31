package com.example.moviesewa.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesewa.data_classes.SearchMovies
import com.example.moviesewa.databinding.SearchItemsBinding

class SearchAdapter() :
    androidx.recyclerview.widget.ListAdapter<SearchMovies, SearchAdapter.SearchViewHolder>(
        MyDiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemsBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding, parent.context)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

    class SearchViewHolder(private val binding: SearchItemsBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            terminateText()
        }

        private fun terminateText() {
            binding.searchOverview.maxLines = 2
            binding.searchOverview.ellipsize = TextUtils.TruncateAt.END
        }

        fun bindData(item: SearchMovies) {
            Glide.with(context).load(image_base_url + item.posterPath).into(binding.searchedPoster)
            binding.searchTitle.text = item.title
            binding.searchReleaseDate.text = item.releaseDate
            binding.searchOverview.text = item.overView
        }
    }

    class MyDiffUtilCallBack : DiffUtil.ItemCallback<SearchMovies>() {
        override fun areItemsTheSame(oldItem: SearchMovies, newItem: SearchMovies): Boolean =
            oldItem.posterPath == newItem.posterPath

        override fun areContentsTheSame(oldItem: SearchMovies, newItem: SearchMovies): Boolean =
            oldItem == newItem
    }

}


