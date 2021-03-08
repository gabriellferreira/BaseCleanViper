package com.gabriellferreira.basecleanviper.presentation.feature.postList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.gabriellferreira.basecleanviper.R
import com.gabriellferreira.basecleanviper.databinding.FragmentPostListBinding
import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseFragment
import com.gabriellferreira.basecleanviper.presentation.feature.postList.adapter.PostListAdapter
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem
import com.gabriellferreira.basecleanviper.presentation.util.extension.controllerComponent
import kotlinx.coroutines.launch

class PostListFragment : BaseFragment<PostListContract.View,
        PostListContract.Presenter>(R.layout.fragment_post_list), PostListContract.View {

    private lateinit var binding: FragmentPostListBinding

    private lateinit var adapter: PostListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        presenter.getPostList()
    }

    override fun showItems(list: PagingData<PostItem>) {
        lifecycleScope.launch {
            adapter.submitData(list)
        }
    }

    private fun setupAdapter() {
        adapter = PostListAdapter(object : PostListAdapter.OnItemActionListener {
            override fun onItemClick(item: PostItem) {
                presenter.onPostItemClicked(item)
            }
        })
        with(binding) {
            recyclerView.adapter = this@PostListFragment.adapter
        }
    }
}