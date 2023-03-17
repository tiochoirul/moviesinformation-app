package com.tcm.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcm.core.BuildConfig
import com.tcm.core.R
import com.tcm.core.databinding.ItemListPopularBinding
import com.tcm.core.domain.model.Popular

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ListViewHolder>() {

    private var listData = ArrayList<Popular>()
    var onItemClick: ((Popular) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Popular>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_popular, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    @Suppress("DEPRECATION")
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListPopularBinding.bind(itemView)
        fun bind(data: Popular) {
            val url = StringBuilder(BuildConfig.IMAGE_URL).append(data.posterPath)
                .toString()
            binding.apply {
                Glide.with(itemView.context)
                    .load(url)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}