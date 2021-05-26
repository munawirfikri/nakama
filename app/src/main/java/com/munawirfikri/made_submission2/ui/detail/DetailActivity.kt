package com.munawirfikri.made_submission2.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.munawirfikri.made_submission2.R
import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.databinding.ActivityDetailBinding
import com.munawirfikri.made_submission2.databinding.ContentDetailBinding
import com.munawirfikri.made_submission2.core.vo.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var activityDetailBinding: ActivityDetailBinding


    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_ANIME = "extra_anime"
        const val EXTRA_TYPE = "extra_type"
        private var statusFavorite = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)
        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val extras = intent.extras
        if (extras != null) {
            val extraId = extras.getString(EXTRA_ANIME)
            if (extraId != null) {
                detailViewModel.setSelectedMovie(extraId)
                if (extras.getString(EXTRA_TYPE) != "Manga") {
                    detailViewModel.getAnime().observe(this, { anime ->
                        if (anime != null) {
                            when (anime) {
                                is Resource.Loading -> activityDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                is Resource.Success -> {
                                    if (anime.data != null) {
                                        detailViewModel.setAnimeResource(anime.data!!)
                                        activityDetailBinding.progressBar.visibility = View.GONE
                                        val state = anime.data!!.isFavorite
                                        statusFavorite = state
                                        setStatusFavorite(state)
                                        populateAnime(anime)
                                    }
                                }
                                is Resource.Error -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })

                } else {
                    detailViewModel.getManga().observe(this, { manga ->
                        if (manga != null) {
                            when (manga) {
                                is Resource.Loading -> activityDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                is Resource.Success -> {
                                    if (manga.data != null){
                                        detailViewModel.setMangaResource(manga.data!!)
                                        activityDetailBinding.progressBar.visibility = View.GONE
                                        val state = manga.data!!.isFavorite
                                        statusFavorite = state
                                        setStatusFavorite(state)
                                        populateManga(manga)
                                    }
                                }
                                is Resource.Error -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
                }
            }
        }

        activityDetailBinding.fab.setOnClickListener(this)
        setStatusFavorite(statusFavorite)
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if(statusFavorite){
            activityDetailBinding.fab.setImageResource(R.drawable.ic_favorite)
        }else{
            activityDetailBinding.fab.setImageResource(R.drawable.ic_favorite_empty)
        }
    }


    private fun populateManga(manga: Resource<Manga>) {
        detailContentBinding.tvDetailScore.text =
            String.format(getString(R.string.score_value), manga.data?.score.toString())
        detailContentBinding.tvDetailTitle.text = manga.data?.title
        detailContentBinding.tvDetailStartDate.text =  manga.data?.startDate?.substring(0, 10)
        if (manga.data?.endDate!=null) {
            detailContentBinding.tvDetailEndDate.text = manga.data?.startDate?.substring(0, 10)
        }else {
            detailContentBinding.tvDetailEndDate.text = "-"
        }
        var status = manga.data?.status
        status = if (status == "true"){
            String.format(getString(R.string.status_value), "On-Going")
        }else{
            String.format(getString(R.string.status_value), "Completed")
        }
        detailContentBinding.tvStatus.text = status
        detailContentBinding.tvDetailDescription.text = manga.data?.synopsis
        Glide.with(this)
            .load(manga.data?.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgDetailPoster)

    }

    private fun populateAnime(anime: Resource<Anime>) {
        detailContentBinding.tvDetailTitle.text = anime.data?.title
        detailContentBinding.tvDetailScore.text =
            String.format(getString(R.string.score_value), anime.data?.score.toString())
        detailContentBinding.tvDetailStartDate.text =  anime.data?.startDate?.substring(0, 10)
        if (anime.data?.endDate!=null) {
            detailContentBinding.tvDetailEndDate.text = anime.data?.startDate?.substring(0, 10)
        }else {
            detailContentBinding.tvDetailEndDate.text = "-"
        }
        var status = anime.data?.status
        status = if (status == "true"){
            String.format(getString(R.string.status_value), "On-Going")
        }else{
            String.format(getString(R.string.status_value), "Completed")
        }
        detailContentBinding.tvStatus.text = status
        detailContentBinding.tvDetailDescription.text = anime.data?.synopsis
        Glide.with(this)
            .load(anime.data?.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgDetailPoster)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {
                statusFavorite = !statusFavorite
                if(statusFavorite){
                    Snackbar.make(activityDetailBinding.root, "Berhasil ditambahkan ke daftar favorit", Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(activityDetailBinding.root, "Berhasil dihapus dari daftar favorit", Snackbar.LENGTH_SHORT).show()
                }
                detailViewModel.setFavoriteAnime()
                detailViewModel.setFavoriteManga()
                setStatusFavorite(statusFavorite)
            }
        }
    }
}


