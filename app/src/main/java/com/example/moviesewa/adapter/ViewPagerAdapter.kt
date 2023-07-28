package com.example.moviesewa.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesewa.fragments.DetailsFragment
import com.example.moviesewa.fragments.MoviesFragment
import com.example.moviesewa.fragments.PeopleFragment
import com.example.moviesewa.search_fragments.SearchMovieFragment

const val ARG_OBJECT = "object"

class ViewPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                SearchMovieFragment()
            }
            1 -> return DetailsFragment()
            2 -> return MoviesFragment()
            else ->
                PeopleFragment()
        }
        return SearchMovieFragment()
    }

//    fun returnSearchFragment(): Fragment {
//        val fragment = SearchMovieFragment()
//        fragment.arguments = Bundle().apply {
//            putString(ARG_OBJECT, query)
//        }
//        return fragment
//    }

}



