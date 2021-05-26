package com.munawirfikri.made_submission2.favorite.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.munawirfikri.made_submission2.favorite.FavoriteViewModel
import com.munawirfikri.made_submission2.favorite.databinding.FragmentFavoriteAnimeBinding
import com.munawirfikri.made_submission2.favorite.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteAnimeFragment : Fragment() {

    private var fragmentAnimeBinding: FragmentFavoriteAnimeBinding? = null
    private var favAnimeAdapter: FavoriteAnimeAdapter? = null

    private val favViewModel: FavoriteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        fragmentAnimeBinding =
            FragmentFavoriteAnimeBinding.inflate(layoutInflater, container, false)
        return fragmentAnimeBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            favAnimeAdapter = FavoriteAnimeAdapter()
            fragmentAnimeBinding?.progressBar?.visibility = View.VISIBLE
            favViewModel.getFavoriteAnime.observe(viewLifecycleOwner, { anime ->
                fragmentAnimeBinding?.progressBar?.visibility = View.GONE
                favAnimeAdapter?.setData(anime)
                fragmentAnimeBinding?.viewEmpty?.root?.visibility = if (anime.isNotEmpty()) View.GONE else View.VISIBLE
                favAnimeAdapter?.notifyDataSetChanged()
            })


            with(fragmentAnimeBinding?.rvFavMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favAnimeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAnimeBinding = null
        favAnimeAdapter = null
    }
}