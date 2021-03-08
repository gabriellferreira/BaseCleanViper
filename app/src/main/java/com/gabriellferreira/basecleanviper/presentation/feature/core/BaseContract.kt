package com.gabriellferreira.basecleanviper.presentation.feature.core

interface BaseContract {

    interface View {
        fun showToast(msg: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter<in T : View> {
        fun attachView(view: T)
        fun detachView()
    }

    interface Router
}