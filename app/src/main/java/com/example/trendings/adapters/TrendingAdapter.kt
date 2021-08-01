package com.example.trendings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendings.core.utils.loadImage
import com.example.trendings.data.local.models.Trending
import com.example.trendings.databinding.RowAdapterTrendingBinding

/**
 * The TrendingAdapter.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class TrendingAdapter :
    RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private val trendingList: MutableList<Trending> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            RowAdapterTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setItems(updates: MutableList<Trending>) {
        if (trendingList.isNotEmpty())
            trendingList.clear()
        trendingList.addAll(updates)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(trendingList[position])
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    inner class TrendingViewHolder(private val binding: RowAdapterTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trending: Trending) = binding.apply {
            rowItemTvName.text = trending.username
            rowItemTvLang.text = trending.language
            rowItemTvLibName.text = trending.libraryName
            rowItemTvStars.text = trending.stars.toString()
            tvItemDetails.text = trending.description
            rowItemImageViewUser.loadImage(trending.imageUrl ?: "")
        }
    }
}