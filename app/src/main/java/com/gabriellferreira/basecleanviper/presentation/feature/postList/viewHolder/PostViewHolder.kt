package com.gabriellferreira.basecleanviper.presentation.feature.postList.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriellferreira.basecleanviper.R
import com.gabriellferreira.basecleanviper.databinding.ItemPostBinding
import com.gabriellferreira.basecleanviper.presentation.feature.postList.adapter.PostListAdapter
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem

class PostViewHolder(
    private val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: PostItem,
        onItemClickListener: PostListAdapter.OnItemActionListener
    ) {
        with(binding) {
            root.setOnClickListener { onItemClickListener.onItemClick(item) }
            title.text = item.title
            body.text = item.body
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            val binding = ItemPostBinding.bind(view)
            return PostViewHolder(binding)
        }
    }
}