package com.gabriellferreira.basecleanviper.presentation.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gabriellferreira.basecleanviper.databinding.ActivityMainBinding
import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseActivity
import com.gabriellferreira.basecleanviper.presentation.util.extension.controllerComponent
import com.gabriellferreira.basecleanviper.presentation.util.extension.viewBinding

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK

            return intent
        }
    }
}