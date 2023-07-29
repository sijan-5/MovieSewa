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

class ViewPagerAdapter(fragment: FragmentActivity, val query :String?) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
      Log.d("qqq", query.toString())
        when (position) {
            0 -> {
                returnSearchFragment()
            }
            1 -> return DetailsFragment()
            2 -> return MoviesFragment()
            else ->
                PeopleFragment()
        }
        return returnSearchFragment()
    }

    fun returnSearchFragment(): Fragment {
        val fragment = SearchMovieFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT, query)
        }
        return fragment
    }

}



