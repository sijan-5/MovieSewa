package com.example.moviesewa.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesewa.Constants
import com.example.moviesewa.adapter.image_base_url
import com.example.moviesewa.databinding.FragmentDetailsBinding
import com.example.moviesewa.mvvm.view_models.MovieDetailViewModel
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
//    val viewModel: MovieViewModel by viewModels()
    val viewModel : MovieDetailViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        var id = 0
        if (bundle != null) {
            id = bundle.getInt(Constants.MOVIE_ID)
        }

        viewModel.getMovieDetail(id)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieDetail.collect { result ->
                handleSuccess(result)
                { successResult ->
                    binding.progressBar.visibility = View.INVISIBLE
                    Glide.with(requireContext()).load(image_base_url + successResult.poster_path)
                        .into(binding.poster)
                    Glide.with(requireContext()).load(image_base_url + successResult.backdrop_path)
                        .into(binding.dropPoster)

                    successResult.apply {
                        binding.title.text = this.title
                        binding.date.text = this.release_date
                        val hour = this.runtime / 60
                        val min = this.runtime % 60
                        binding.movieDuration.text = "$hour h $min min"

                        binding.genre.text = this.genres.joinToString(separator = ",", limit = 3){
                            it.name
                        }
                        binding.tagLine.text = this.tagline
                        binding.overView.maxLines = 2
                        binding.overView.ellipsize = TextUtils.TruncateAt.END
                        binding.overView.text = this.overview
                        val percentage = this.popularity.toInt() % 100
                        binding.popularityRate.progress = percentage
                        binding.progressPercentage.text = percentage.toString() + "%"
                    }
                }
            }
        }
    }

    private fun <T> handleSuccess(result: ResponseResult<T>, onSuccess: (T) -> Unit) {
        handleResult(result, success = onSuccess)
        {
            binding.progressBar.visibility = View.INVISIBLE
            Log.d("details error", it!!)
        }
    }

    private fun <T> handleResult(
        result: ResponseResult<T>,
        success: (T) -> Unit,
        onFailure: (String?) -> Unit
    ) {
        when (result) {
            is ResponseResult.Success -> success(result.successData)
            is ResponseResult.Failure -> onFailure(result.error)
            is ResponseResult.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }


}

