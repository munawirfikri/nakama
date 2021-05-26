package com.munawirfikri.made_submission2.ui.anime

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.munawirfikri.made_submission2.core.R
import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.databinding.ItemsBinding
import com.munawirfikri.made_submission2.ui.detail.DetailActivity

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private var listData = ArrayList<Anime>()

    fun setData(newListData: List<Anime>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val itemsBinding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    class AnimeViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            with(binding) {
                tvItemTitle.text = anime.title
                tvItemStartDate.text = anime.startDate?.substring(0, 4)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ANIME, anime.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_TYPE, anime.type)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(anime.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun getItemCount() = listData.size
}