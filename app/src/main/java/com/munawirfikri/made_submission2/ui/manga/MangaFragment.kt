package com.munawirfikri.made_submission2.ui.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.munawirfikri.made_submission2.databinding.FragmentMangaBinding
import com.munawirfikri.made_submission2.core.vo.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class MangaFragment : Fragment() {

    private lateinit var fragmentMangaBinding: FragmentMangaBinding
    private val mangaViewModel: MangaViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMangaBinding = FragmentMangaBinding.inflate(layoutInflater, container, false)
        return fragmentMangaBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val mangaAdapter = MangaAdapter()


            mangaViewModel.mangaUseCase.observe(viewLifecycleOwner, { manga ->
                if (manga != null){
                    when(manga) {
                        is Resource.Loading -> fragmentMangaBinding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            fragmentMangaBinding.progressBar.visibility = View.GONE
                            mangaAdapter.setData(manga.data)
                            mangaAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            fragmentMangaBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

            with(fragmentMangaBinding.rvManga) {
                this.layoutManager = GridLayoutManager(context, 3)
                this.setHasFixedSize(true)
                this.adapter = mangaAdapter
            }
        }
    }
}