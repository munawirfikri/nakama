package com.munawirfikri.made_submission2.ui.manga

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.munawirfikri.made_submission2.core.R
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.core.databinding.ItemsBinding
import com.munawirfikri.made_submission2.ui.detail.DetailActivity

class MangaAdapter : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    private var listData = ArrayList<Manga>()

    fun setData(newListData: List<Manga>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val itemsBinding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = listData[position]
        holder.bind(manga)
    }


    class MangaViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Manga) {
            with(binding) {
                tvItemTitle.text = manga.title
                tvItemStartDate.text = manga.startDate?.substring(0, 4)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ANIME, manga.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_TYPE, manga.type)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(manga.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun getItemCount() = listData.size
}