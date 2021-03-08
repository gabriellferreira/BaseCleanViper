package com.gabriellferreira.basecleanviper.presentation.feature.postDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gabriellferreira.basecleanviper.databinding.ActivityPostDetailsBinding
import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseActivity
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity.PostDetailsViewItem
import com.gabriellferreira.basecleanviper.presentation.util.extension.controllerComponent
import com.gabriellferreira.basecleanviper.presentation.util.extension.viewBinding

class PostDetailsActivity : BaseActivity<PostDetailsContract.View, PostDetailsContract.Presenter>(),
    PostDetailsContract.View {

    private val binding by viewBinding(ActivityPostDetailsBinding::inflate)

    private val postId: String by lazy {
        intent.getStringExtra(EXTRA_POST_ID)
            ?: throw Exception("Invalid postId - PostDetailsActivity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.fetch(postId)
    }

    override fun setupView(item: PostDetailsViewItem) {
        supportActionBar?.title = item.toolbarTitle
        with(binding) {
            title.text = item.title
            body.text = item.body
            userInfo.text = item.userInfo
        }
    }

    companion object {
        const val EXTRA_POST_ID = "EXTRA_POST_ID"

        fun createIntent(context: Context, postId: String): Intent {
            val intent = Intent(context, PostDetailsActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, postId)
            return intent
        }
    }
}