package com.example.moviesewa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesewa.R
import com.example.moviesewa.data_classes.MovieData

class MovieAdapter(val movieList : MutableList<MovieData>, val context : Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{

    private val image_base_url = "https://image.tmdb.org/t/p/w500"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_movie_recycler_view_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val item = movieList[position]

        holder.movieTitle.text = item.title
        holder.movieReleaseDate.text = item.releaseData

        Glide.with(context).load(image_base_url + item.posterPath).into(holder.posterView)
    }


    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
         val posterView: ImageView = itemView.findViewById(R.id.posterView)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
         val movieReleaseDate : TextView = itemView.findViewById(R.id.movieReleaseDate)

    }

}