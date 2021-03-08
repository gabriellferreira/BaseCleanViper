package com.gabriellferreira.basecleanviper.presentation.feature.postList.entity

import androidx.recyclerview.widget.DiffUtil

class PostItem(
    val id: String,
    val title: String,
    val body: String,
)

class PostItemDiff : DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
        oldItem.title == newItem.title &&
                oldItem.body == newItem.body
}