package com.example.moviesewa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.data_classes.Result
import com.example.moviesewa.databinding.FragmentHomeBinding
import com.example.moviesewa.mvvm.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentHomeBinding
    private lateinit var switchOnOff: SwitchCompat
    private lateinit var dataList : MutableList<MovieData>
    val viewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchOnOff = view.findViewById(R.id.switchOnOff)
        switchOnOff.setOnCheckedChangeListener{_, checked ->
            when{
                checked -> Toast.makeText(requireContext(), "week", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(requireContext(), "day", Toast.LENGTH_SHORT).show()
            }
        }

        fetchData()
        setUpRecyclerView()
    }

    private fun fetchData()
    {
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                val response = viewModel.getMovies()
                if (response.isSuccessful) {
                    Log.d("size",makeDataList(response.body()!!.results).toString())
                }
            }
        }
    }

    fun makeDataList( results : List<Result>) : Int
    {
        dataList = mutableListOf<MovieData>()
        results.map { result ->
            dataList.add(MovieData(result.poster_path, result.title, result.release_date))
        }
        return  dataList.size
    }

    private fun setUpRecyclerView()
    {
        binding.movesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}