package com.example.androidarchitecturedemo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.viewModel.activity.DetailViewModel

class DetailActivity : AppCompatActivity() {

    enum class ExtraKey(val key: String) {
        RepositoryInfo("RepositoryInfo")
    }

//    private val viewModel: DetailViewModel by viewModels()


    private val viewModel: DetailViewModel by viewModels()

    private lateinit var toolbar: Toolbar
    private lateinit var webView: WebView
    private lateinit var favoriteMenuItem: MenuItem

    private lateinit var repositoryInfo: GitRepositoryInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getExtras()
        linkViews()
        setupViews()
        bindViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) { return false }
        menuInflater.inflate(R.menu.detail_menu, menu)
        favoriteMenuItem = menu.findItem(R.id.favorite)
        viewModel.onCreateOptionsMenu()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) { return false }
        if(item.itemId == R.id.favorite){
            viewModel.onFavoriteSelected()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getExtras() {
        repositoryInfo = intent.getSerializableExtra(ExtraKey.RepositoryInfo.key) as GitRepositoryInfo
    }

    private fun linkViews() {
        toolbar = findViewById(R.id.toolbar)
        webView = findViewById(R.id.web_view)

    }

    private fun setupViews() {
        title = repositoryInfo.name
        webView.loadUrl(repositoryInfo.url.toString())
    }

    private fun bindViewModel() {

        viewModel.setup(repositoryInfo.id)

        viewModel.isFavorite.observe(this) {
            setFavorite(it)
        }
    }

    private fun setFavorite(isFavorite: Boolean) {

        val iconId = if (isFavorite)
            R.drawable.ic_baseline_favorite_24
        else R.drawable.ic_baseline_favorite_border

        favoriteMenuItem.setIcon(iconId)

    }
}