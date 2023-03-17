package com.tcm.moviesinformationapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.tcm.core.domain.model.Popular
import com.tcm.moviesinformationapp.R
import com.tcm.moviesinformationapp.databinding.ActivityDetailPopularBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPopularActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPopularBinding
    private val detailPopularViewModel: DetailPopularViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPopularBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        @Suppress("DEPRECATION") val detailPopular = intent.getParcelableExtra<Popular>(EXTRA_DATA)
        showDetailMovie(detailPopular)
    }

    private fun showDetailMovie(detailPopular: Popular?) {
        detailPopular.let {
            if (detailPopular != null) {
                supportActionBar?.title = detailPopular.title

                binding.content.apply {
                    tvDetailOriginalTitle.text = detailPopular.originalTitle
                    tvDetailDescription.text = detailPopular.overview
                    tvDetailReleaseDate.text = detailPopular.releaseDate
                    tvDetailRating.text =
                        StringBuilder(detailPopular.originalTitle).append("/10")
                }
            }


            val url =
                StringBuilder(getString(R.string.link_image)).append(detailPopular?.posterPath)
                    .toString()
            Glide.with(this@DetailPopularActivity)
                .load(url)
                .into(binding.ivDetailImage)

            var statusFavorite = detailPopular?.isFavorite ?: false
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                if (detailPopular != null) {
                    detailPopularViewModel.setFavoritePopular(detailPopular, statusFavorite)
                }
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_fill
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}