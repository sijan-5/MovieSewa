package com.example.moviesewa

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesewa.adapter.ViewPagerAdapter
import com.example.moviesewa.data_classes.SearchItems
import com.example.moviesewa.databinding.ActivitySearchBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    val viewModel: MovieViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var pager: ViewPager2
    private lateinit var binding: ActivitySearchBinding

    val tabLayoutItemsName = arrayListOf<String>("Movies", "People", "TV Shows", "Collections")
    val list = mutableListOf<SearchItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        TabLayoutMediator(binding.myTabLayout, binding.pager) { tab, position ->
            tab.text = tabLayoutItemsName[position]
        }.attach()

        lifecycleScope.launch {
            viewModel.searchMovie("barbie", "78cec9d5b33781dd70509ca0e6e88019")
                .collect() { result ->
                    when (result) {
                        is ResponseResult.Loading -> Log.d("i am not", "loading")
                        is ResponseResult.Failure -> Log.d("i am not", "failure")
                        is ResponseResult.Success -> {
                            result.successData.results.map {
                                list.add(SearchItems(it.poster_path, it.title, it.release_date, it.overview))
                            }
                            setUpViewPagerAdapter(list)
                        }
                    }
                }
        }


        binding.myTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        setUpViewPagerAdapter(list)
                        Toast.makeText(this@SearchActivity, "${list.size}", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        setUpViewPagerAdapter(list)
                        Toast.makeText(this@SearchActivity, "secTab", Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        Toast.makeText(this@SearchActivity, "thirdTab", Toast.LENGTH_SHORT).show()

                    }
                    3 -> {
                        Toast.makeText(this@SearchActivity, "fourthTab", Toast.LENGTH_SHORT).show()
                    }
                    // Add cases for other tabs as needed
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Code to execute when a tab is unselected (optional)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Code to execute when a tab is reselected (optional)
            }
        })

    }

    fun setUpViewPagerAdapter(list : List<SearchItems>)
    {
        viewPagerAdapter = ViewPagerAdapter(list, this)
        binding.pager.adapter = viewPagerAdapter
    }


}