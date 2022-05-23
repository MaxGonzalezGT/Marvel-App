package com.android.marvel.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.R
import com.android.marvel.adapters.CharactersAdapter
import com.android.data.data.dto.Result
import com.android.data.util.Constants
import com.android.data.util.Constants.Companion.CHARACTER_NOT_FOUND
import com.android.data.util.NetworkListener
import com.android.marvel.databinding.FragmentMarvelCharactersBinding
import com.android.marvel.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MarvelCharactersFragment : Fragment(){

    private var _binding: FragmentMarvelCharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { CharactersAdapter() }
    private lateinit var networkListener: NetworkListener
    var repeat = 3
    var paginate = 0
    var searching = false
    var list = arrayListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.marvel_characters)
        _binding = FragmentMarvelCharactersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext())
                .collect { status ->
                    Log.d("NetworkListener", status.toString())
                    mainViewModel.networkStatus = status
                    mainViewModel.showNetworkStatus()
                    //Set search bar when internet connection is available
                    setHasOptionsMenu(status)
                    requestData()
                }
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(getActivity(), 2)

        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.setLayoutManager(layoutManager)

        binding.recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1 && !searching)
                {
                    paginate += Constants.LIMIT.toInt()
                    mainViewModel.getCharacters(paginate)
                    requestData()
                }
            }
        })

        showProgressBar()
    }


    private fun requestData(){
        mainViewModel.getCharacters(paginate)
        CoroutineScope(Dispatchers.Main).launch {
            repeat(repeat){
                mainViewModel._charactersResponse.collect{value->
                    when {
                        value.isLoading -> {
                            showProgressBar()
                        }
                        value.error.isNotBlank() -> {
                            hideProgressBar(value.error)
                        }
                        value.characters != null -> {
                            hideProgressBar()
                            value.characters?.let {
                                mAdapter.setData(it)
                            }
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    private fun requestSearchAPIData(query:String=""){
        mainViewModel.searchCharacters(query)
        CoroutineScope(Dispatchers.Main).launch {
            repeat(repeat){
                mainViewModel._charactersResponse.collect{value->
                    when {
                        value.isLoading -> {
                            showProgressBar()
                        }
                        value.error.isNotBlank() -> {
                            hideProgressBar(value.error)
                        }
                        value.characters != null -> {
                            hideProgressBar()
                            value.characters?.let {
                                mAdapter.setData(it)
                            }
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    private fun hideProgressBar(error:String= "") {
        binding.progressBar.visibility = View.GONE
        Log.d("error", error)
        if(error != CHARACTER_NOT_FOUND && error.isBlank()){
            binding.recyclerview.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.character_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true

        if (searchView != null) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query != null) {
                        searching = true
                        requestSearchAPIData(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (searchView.query.isEmpty()) {
                        searching = false
                        requestData()
                    }
                    return false
                }
            })
        }
    }
}