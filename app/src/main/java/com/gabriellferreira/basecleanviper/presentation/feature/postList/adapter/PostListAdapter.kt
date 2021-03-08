package com.gabriellferreira.basecleanviper.presentation.feature.postList.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItemDiff
import com.gabriellferreira.basecleanviper.presentation.feature.postList.viewHolder.PostViewHolder

class PostListAdapter(
    private val onItemClickListener: OnItemActionListener
) : PagingDataAdapter<PostItem, PostViewHolder>(PostItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder.create(parent)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClickListener)
        }
    }

    interface OnItemActionListener {
        fun onItemClick(item: PostItem)
    }
}