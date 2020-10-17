package com.example.androidarchitecturedemo.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.fragment.SearchFragment
import com.example.androidarchitecturedemo.viewModel.activity.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SearchFragment.OnItemClickListener {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        linkViews()
        setupViews()
        bindViewModel()

    }

    private fun linkViews() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    private fun setupViews() {

        bottomNavigationView.setupWithNavController(navController)

    }

    private fun bindViewModel() {

        viewModel.repositoryInfo.observe(this) {
            moveToDetailWithRepositoryInfo(it)
        }
    }

    private fun moveToDetailWithRepositoryInfo(repositoryInfo: GitRepositoryInfo) {

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ExtraKey.RepositoryInfo.key, repositoryInfo)
        }
        startActivity(intent)
    }

    override fun onItemClickListener(item: GitRepositoryInfo) {
        viewModel.onSelectedItem(item)
    }

}