package com.gabriellferreira.basecleanviper.presentation.feature.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<V, P>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId),
    BaseContract.View where V : BaseContract.View, P : BaseContract.Presenter<V> {

    @Inject
    lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeAttachView()
    }

    @Suppress("UNCHECKED_CAST")
    private fun safeAttachView() {
        try {
            presenter.attachView(this as V)
        } catch (e: ClassCastException) {
            throw RuntimeException("Type V must be the same type of this class", e)
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showProgress() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showProgress()
        }
    }

    override fun hideProgress() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).hideProgress()
        }
    }

    override fun showToast(msg: String) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showToast(msg)
        }
    }
}