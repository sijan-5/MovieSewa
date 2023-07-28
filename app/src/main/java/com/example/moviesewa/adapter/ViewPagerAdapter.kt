package com.example.moviesewa.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.moviesewa.R
import com.example.moviesewa.data_classes.SearchItems
import com.example.moviesewa.fragments.PeopleFragment

const val ARG_OBJECT = "object"
class ViewPagerAdapter(val list : List<SearchItems>, val context : Context) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_items, parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        val item = list[position]
        Glide.with(context).load(image_base_url + item.posterPath).into(holder.posterImage)

        holder.overView.text  = item.overView
        holder.title.text = item.title
        holder.releaseDate.text = item.releaseData
    }

    inner class ViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val posterImage : ImageView = itemView.findViewById(R.id.searchedPoster)
        val title : TextView = itemView.findViewById(R.id.searchTitle)
        val releaseDate : TextView = itemView.findViewById(R.id.searchTitle)
        val overView : TextView = itemView.findViewById(R.id.search_overview)
    }

}



//    override fun getItemCount(): Int {
//        return 4
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        val fragment = PeopleFragment()
//        fragment.arguments = Bundle().apply {
//            // Our object is just an integer :-P
//            putInt(ARG_OBJECT, position + 1)
//        }
//        return fragment
//    }
