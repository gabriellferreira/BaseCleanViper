package com.gabriellferreira.basecleanviper.presentation.feature.core

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T : BaseContract.View, R : BaseContract.Router> : CoroutineScope,
    BaseContract.Presenter<T> {

    @Inject
    lateinit var router: R
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main.immediate

    protected var view: T? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    @CallSuper
    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
        job.cancel()
    }
}