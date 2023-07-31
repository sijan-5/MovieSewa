package com.example.moviesewa.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesewa.Constants
import com.example.moviesewa.fragments.DetailsFragment
import com.example.moviesewa.fragments.MoviesFragment
import com.example.moviesewa.fragments.PeopleFragment
import com.example.moviesewa.search_fragments.SearchMovieFragment



class ViewPagerAdapter(fragment: FragmentActivity, val query :String?) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> {
                getSearchFragment()
            }
            1 ->  PeopleFragment()
            2 ->  PeopleFragment()
            else ->
                PeopleFragment()
        }
    }

    private fun getSearchFragment(): Fragment {
        val fragment = SearchMovieFragment()
        fragment.arguments = Bundle().apply {
            putString(Constants.SEARCH_QUERY, query)
        }
        return fragment
    }
}



