package com.example.moviesewa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesewa.R
import com.example.moviesewa.adapter.MovieAdapter
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.databinding.FragmentHomeBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var switchOnOff: SwitchCompat
    private lateinit var dataList: MutableList<MovieData>
    val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchOnOff = view.findViewById(R.id.switchOnOff)
        switchOnOff.setOnCheckedChangeListener { _, checked ->
            when {
                checked -> Toast.makeText(requireContext(), "week", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(requireContext(), "day", Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchButton.setOnClickListener {
            val inputQuery = binding.searchEditText.text.toString()
            viewModel.queryLiveData.value = inputQuery
            val searchQueryBundle = bundleOf("sKey" to inputQuery)
            findNavController().navigate(R.id.action_homeFragment_to_searchActivity,searchQueryBundle )
        }

        setUpRecyclerView()
        lifecycleScope.launch {
            viewModel.getMovies().collect{ result ->

                when(result)
                {
                    is ResponseResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is ResponseResult.Success ->
                    {
                        val movieList = mutableListOf<MovieData>()
                        val movies = result.successData.results
                        movies.map {
                            movieList.add(
                                MovieData(
                                    it.poster_path,
                                    it.title,
                                    it.release_date,
                                    it.id
                                )
                            )
                        }
                        binding.movesRecyclerView.adapter =
                            MovieAdapter(movieList, requireContext())
                            {
                                val bundle = bundleOf(
                                    "ID" to it
                                )
                                findNavController().navigate(
                                    R.id.action_homeFragment_to_detailsFragment,
                                    bundle
                                )
                            }
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    is ResponseResult.Failure ->
                    {
                        Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        binding.movesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


}