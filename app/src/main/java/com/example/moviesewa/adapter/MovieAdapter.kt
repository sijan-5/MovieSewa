package com.example.moviesewa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesewa.R
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.databinding.TrendingMovieRecyclerViewItemBinding


const val image_base_url = "https://image.tmdb.org/t/p/w500"
class MovieAdapter(val  onClick : (Int) ->Unit) : androidx.recyclerview.widget.ListAdapter<MovieData, MovieAdapter.MovieViewHolder>(DiffUtilCallBack())
{
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = TrendingMovieRecyclerViewItemBinding.inflate(inflator, parent, false)
        return MovieViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
        holder.itemView.setOnClickListener {
            this.onClick(item.movieId)
        }
    }

    class  DiffUtilCallBack  : DiffUtil.ItemCallback<MovieData>()
    {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean  =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean  =
            oldItem == newItem
    }
    
    class MovieViewHolder(private val binding : TrendingMovieRecyclerViewItemBinding, val context: Context) : RecyclerView.ViewHolder(binding.root)
    {
        fun bindData(item : MovieData)
        {
            binding.movieTitle.text = item.title
            binding.movieReleaseDate.text= item.releaseDate
            Glide.with(context).load(image_base_url + item.posterPath).into(binding.posterView)
        }
    }

}