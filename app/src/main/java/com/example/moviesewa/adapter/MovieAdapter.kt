package com.example.moviesewa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesewa.R
import com.example.moviesewa.data_classes.MovieData

class MovieAdapter(val movieList : ArrayList<MovieData>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_movie_recycler_view_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {

        return 5
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


    }


    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        private val posterView = itemView.findViewById<ImageView>(R.id.posterView)
        private val movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        private val movieReleaseDate = itemView.findViewById<TextView>(R.id.movieReleaseDate)

    }

}