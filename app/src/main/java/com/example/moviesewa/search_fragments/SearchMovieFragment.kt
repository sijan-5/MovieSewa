package com.example.moviesewa.search_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesewa.Constants
import com.example.moviesewa.adapter.SearchAdapter
import com.example.moviesewa.data_classes.SearchMovies
import com.example.moviesewa.databinding.FragmentSearchMovieBinding
import com.example.moviesewa.mvvm.view_models.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import com.example.moviesewa.mvvm.view_models.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchMovieBinding
    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        arguments?.takeIf { it.containsKey(Constants.SEARCH_QUERY) }?.apply {
           val  query = getString(Constants.SEARCH_QUERY).toString()
            getSearchResponse(query)
        }
    }

    private fun getSearchResponse(query :String) {
        val searchMovieList = mutableListOf<SearchMovies>()
        viewModel.searchMovieCollection(query)
        lifecycleScope.launch {
            viewModel.searchState
                .collect { result ->
                    when (result) {
                        is ResponseResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is ResponseResult.Failure ->{
                            Log.d("search error", "${result.error}")
                            binding.progressBar.visibility = View.GONE
                        }

                        is ResponseResult.Success -> {
                            binding.progressBar.visibility = View.GONE
                            result.successData.results.map {
                                searchMovieList.add(
                                    SearchMovies(
                                        it.poster_path,
                                        it.title,
                                        it.release_date,
                                        it.overview
                                    )
                                )
                                val adapter = SearchAdapter()
                                adapter.submitList(searchMovieList)
                                binding.searchMovieRecyclerView.adapter = adapter
                            }
                        }
                    }
                }
        }
    }

    private fun setUpRecyclerView() {
        binding.searchMovieRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}