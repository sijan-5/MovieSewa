package com.example.moviesewa.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesewa.R

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_items,parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {

        return 7

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {


    }

    class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val posterImage : ImageView = itemView.findViewById(R.id.searchedPoster)
        val title : TextView = itemView.findViewById(R.id.searchTitle)
        val releaseDate : TextView = itemView.findViewById(R.id.searchTitle)
        val overView : TextView = itemView.findViewById(R.id.search_overview)
    }

}