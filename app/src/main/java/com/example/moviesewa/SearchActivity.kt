package com.example.moviesewa

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesewa.adapter.ViewPagerAdapter
import com.example.moviesewa.databinding.ActivitySearchBinding
import com.example.moviesewa.mvvm.view_models.MovieViewModel
import com.example.moviesewa.mvvm.view_models.SearchViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects


@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    val viewModel: SearchViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivitySearchBinding

    private val tabLayoutItems = arrayListOf<String>("Movies", "People", "TV Shows", "Collections")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val receivedSearchQuery = intent.getStringExtra(Constants.SEARCH_QUERY)
        setUpViewpagerAndTabLayout(receivedSearchQuery)
    }


    private fun setUpViewpagerAndTabLayout(query : String?)
    {
        viewPagerAdapter = ViewPagerAdapter(this, query)
        binding.pager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.myTabLayout, binding.pager) { tab, position ->
            tab.text = tabLayoutItems[position]
        }.attach()
    }

}