package com.example.moviesewa.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesewa.adapter.image_base_url
import com.example.moviesewa.databinding.FragmentDetailsBinding
import com.example.moviesewa.mvvm.MovieViewModel
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    val viewModel : MovieViewModel by viewModels()
    private lateinit var binding : FragmentDetailsBinding

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
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        var id : Int = 0
        if(bundle != null)
        {
            id = bundle.getInt("ID")
        }

        lifecycleScope.launch {

            viewModel.getMovieDetails(id).collect{result ->

                when(result)
                {
                    is ResponseResult.Loading -> Log.d("loading", "Loading....")
                    is ResponseResult.Success -> {
                        Glide.with(requireContext()).load(image_base_url + result.successData.poster_path).into(binding.poster)
                        Glide.with(requireContext()).load(image_base_url + result.successData.backdrop_path).into(binding.dropPoster)
                    }

                    is ResponseResult.Failure -> Log.d("failure", result.error.toString())
                }
            }
        }
    }

}

