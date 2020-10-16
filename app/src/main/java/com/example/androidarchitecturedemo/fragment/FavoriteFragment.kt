package com.example.androidarchitecturedemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.dummy.DummyContent2
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.viewModel.fragment.SearchViewModel

/**
 * A fragment representing a list of Items.
 */
class FavoriteFragment : Fragment() {

    private val viewModel = SearchViewModel()

    private var columnCount = 1

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_favorite_list,
            container,
            false
        ) as RecyclerView
        linkViews(view)

        // Set the adapter

        return view
    }

    private fun linkViews(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    private fun setupViews() {

    }

    private fun bindViewModel() {

        viewModel.gitRepositoryList.observe(viewLifecycleOwner, Observer {

        })

    }

    private fun updateList(list: List<GitRepositoryInfo>) {

        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemRecyclerViewAdapter2(DummyContent2.ITEMS)
        }

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}