package com.example.moviesewa.adapter

import android.content.Context
import android.text.Layout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesewa.R
import com.example.moviesewa.data_classes.SearchMovies
import kotlin.math.truncate

class SearchAdapter(val list : List<SearchMovies>,val  context :Context) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_items,parent, false)
        return SearchViewHolder(view).apply {
            terminateText()
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = list[position]

        Glide.with(context).load(image_base_url + item.posterPath).into(holder.posterImage)
        holder.title.text = item.title
        holder.releaseDate.text = item.releaseDate
        holder.overView.text = item.overView
    }

    class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val posterImage : AppCompatImageView = itemView.findViewById(R.id.searchedPoster)
        val title : TextView = itemView.findViewById(R.id.searchTitle)
        val releaseDate : TextView = itemView.findViewById(R.id.search_ReleaseDate)
        val overView : TextView = itemView.findViewById(R.id.search_overview)

        fun terminateText()
        {
            overView.maxLines = 2
            overView.ellipsize = TextUtils.TruncateAt.END
        }
    }

}