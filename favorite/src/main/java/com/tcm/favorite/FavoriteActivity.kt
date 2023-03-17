package com.tcm.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcm.core.ui.PopularAdapter
import com.tcm.favorite.databinding.ActivityFavoriteBinding
import com.tcm.moviesinformationapp.detail.DetailPopularActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(R.string.my_favorite_movies)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val popularAdapter = PopularAdapter()
        popularAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailPopularActivity::class.java)
            intent.putExtra(DetailPopularActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this) { favoriteMovie ->
            popularAdapter.setData(favoriteMovie)
        }

        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = popularAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}