package com.example.moviesewa.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesewa.R
import com.example.moviesewa.adapter.MovieAdapter
import com.example.moviesewa.data_classes.Movie
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.databinding.FragmentHomeBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()

    private val adapter =
        MovieAdapter()
        {
            val bundle = bundleOf(
                "ID" to it
            )
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getLatestTvId().collect { result ->
                when (result) {
                    is ResponseResult.Failure -> Log.d("f", "f")
                    is ResponseResult.Success -> viewModel.getLatestTvId()
                    is ResponseResult.Loading -> Log.d("f", "l")
                }
            }
        }

        binding.switchOnOff.setOnCheckedChangeListener { _, checked ->
            when {
                checked -> fetchDailyTrending(context!!.getString(R.string.weekly))
                else -> fetchDailyTrending(context!!.getString(R.string.daily))
            }
        }
        binding.searchButton.setOnClickListener {
            val inputQuery = binding.searchEditText.text.toString()
            val searchQueryBundle = bundleOf("sKey" to inputQuery)
            findNavController().navigate(
                R.id.action_homeFragment_to_searchActivity,
                searchQueryBundle
            )
        }
        fetchDailyTrending(context!!.getString(R.string.weekly))
    }

    private fun fetchDailyTrending(time_window: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies(time_window).collect { result ->
                handleResult(result) { successResult ->

                    val movieList = successResult.results.map {
                        it.toMovieData()
                    }

                    adapter.submitList(movieList)
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }


    private fun <T> handleResult(result: ResponseResult<T>, onSuccess: (T) -> Unit) {
        handleSuccess(result, success = onSuccess)
        { failure ->
            binding.progressBar.visibility = View.INVISIBLE
            Log.d("movieError", failure)

        }
    }

    private fun <T> handleSuccess(
        result: ResponseResult<T>,
        success: (T) -> Unit,
        onFailure: (String) -> Unit
    ) {
        when (result) {
            is ResponseResult.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ResponseResult.Failure -> onFailure(result.error.toString())
            is ResponseResult.Success -> success(result.successData)
        }
    }


    private fun Movie.toMovieData(): MovieData {
        return MovieData(
            this.poster_path,
            this.title,
            this.release_date,
            this.id
        )
    }

    private fun setUpRecyclerView() {
        binding.movesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            adapter = this@HomeFragment.adapter
        }
    }

}




