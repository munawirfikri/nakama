package com.munawirfikri.made_submission2.favorite.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.munawirfikri.made_submission2.favorite.FavoriteViewModel
import com.munawirfikri.made_submission2.favorite.databinding.FragmentFavoriteMangaBinding
import com.munawirfikri.made_submission2.favorite.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteMangaFragment : Fragment() {

    private var fragmentMangaBinding: FragmentFavoriteMangaBinding? = null
    private var favMangaAdapter: FavoriteMangaAdapter? = null
    private val favViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        loadKoinModules(favoriteModule)
        fragmentMangaBinding = FragmentFavoriteMangaBinding.inflate(layoutInflater, container, false)
        return fragmentMangaBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            favMangaAdapter = FavoriteMangaAdapter()
            fragmentMangaBinding?.progressBar?.visibility = View.VISIBLE
            favViewModel.getFavoriteManga.observe(viewLifecycleOwner, {manga ->
                fragmentMangaBinding?.progressBar?.visibility = View.GONE
                favMangaAdapter?.setData(manga)
                fragmentMangaBinding?.viewEmpty?.root?.visibility = if (manga.isNotEmpty()) View.GONE else View.VISIBLE
                favMangaAdapter?.notifyDataSetChanged()
            })


            with(fragmentMangaBinding?.rvFavTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favMangaAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMangaBinding = null
        favMangaAdapter = null
    }

}