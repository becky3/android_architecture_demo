package com.example.androidarchitecturedemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.viewModel.fragment.SearchViewModel

class SearchFragment : Fragment() {

    interface OnItemClickListener{
        fun onItemClickListener(item: GitRepositoryInfo)
    }

    private val viewModel :SearchViewModel by viewModels()

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
                searchView.clearFocus()
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
                val activity = activity as? OnItemClickListener ?: return
                activity.onItemClickListener(item)
            }
        }

    }

    private fun bindViewModel() {

        viewModel.gitRepositoryList.observe(viewLifecycleOwner) {
            updateList(it)
        }

    }

    private fun updateList(list: List<GitRepositoryInfo>) {

        recyclerViewAdapter.updateItems(list)

    }
}