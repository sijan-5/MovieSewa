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
import com.example.moviesewa.adapter.ARG_OBJECT
import com.example.moviesewa.adapter.SearchAdapter
import com.example.moviesewa.data_classes.SearchMovies
import com.example.moviesewa.databinding.FragmentSearchMovieBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

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
        getSearchResponse()
    }

    private fun getSearchResponse() {
        val searchMovieList = mutableListOf<SearchMovies>()
        lifecycleScope.launch {
            viewModel.searchMovie("titanic")
                .collect { result ->
                    when (result) {
                        is ResponseResult.Loading -> Log.d("i am not", "loading")
                        is ResponseResult.Failure -> Log.d("i am not", "failure")
                        is ResponseResult.Success -> {
                            result.successData.results.map {
                                searchMovieList.add(
                                    SearchMovies(
                                        it.poster_path,
                                        it.title,
                                        it.release_date,
                                        it.overview
                                    )
                                )
                                binding.searchMovieRecyclerView.adapter =
                                    SearchAdapter(list = searchMovieList, requireContext())
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