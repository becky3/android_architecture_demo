package com.example.androidarchitecturedemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.viewModel.fragment.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewAdapter: GitRepositoryInfoRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(
            R.layout.fragment_search_list,
            container,
            false
        ) as ConstraintLayout

        linkViews(layout)
        setupViews()
        bindViewModel()

        return layout
    }

    private fun linkViews(layout: ConstraintLayout) {
        recyclerView = layout.findViewById(R.id.list)
        searchView = layout.findViewById(R.id.searchView)

    }

    private fun setupViews() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.onQueryTextSubmit(query)
                return true
            }
        })

        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = GitRepositoryInfoRecyclerViewAdapter()
        }
        recyclerViewAdapter = recyclerView.adapter as GitRepositoryInfoRecyclerViewAdapter
        
        recyclerViewAdapter.itemClickListener = object:GitRepositoryInfoRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClickListener(item: GitRepositoryInfo) {
                Toast.makeText(context, "${item.name}がタップされました", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun bindViewModel() {

        viewModel.gitRepositoryList.observe(viewLifecycleOwner, Observer {
            updateList(it)
        })

    }

    private fun updateList(list: List<GitRepositoryInfo>) {

        recyclerViewAdapter.updateItems(list)

    }
}