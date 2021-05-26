package com.munawirfikri.made_submission2.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.munawirfikri.made_submission2.databinding.FragmentAnimeBinding
import com.munawirfikri.made_submission2.core.vo.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnimeFragment : Fragment() {

    private var fragmentAnimeBinding: FragmentAnimeBinding? = null
    private var animeAdapter: AnimeAdapter? = null
    private val animeViewModel: AnimeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentAnimeBinding = FragmentAnimeBinding.inflate(layoutInflater, container, false)
        return fragmentAnimeBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            animeAdapter = AnimeAdapter()
            animeViewModel.animeUseCase.observe(viewLifecycleOwner, {anime ->
                if (anime != null){
                    when (anime){
                        is Resource.Loading-> fragmentAnimeBinding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            fragmentAnimeBinding?.progressBar?.visibility = View.GONE
                            animeAdapter?.setData(anime.data)
                            animeAdapter?.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            fragmentAnimeBinding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })


            with(fragmentAnimeBinding?.rvAnime) {
                this?.layoutManager = GridLayoutManager(context, 3)
                this?.setHasFixedSize(true)
                this?.adapter = animeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAnimeBinding = null
        animeAdapter = null
    }

}