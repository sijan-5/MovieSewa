package com.example.moviesewa

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesewa.adapter.ViewPagerAdapter
import com.example.moviesewa.databinding.ActivitySearchBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    val viewModel: MovieViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivitySearchBinding
    private lateinit var receiveSearchQuery: String
    val tabLayoutItems = arrayListOf<String>("Movies", "People", "TV Shows", "Collections")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val received = intent.getStringExtra("sKey")
        setUpViewpagerAndTabLayout(received)
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