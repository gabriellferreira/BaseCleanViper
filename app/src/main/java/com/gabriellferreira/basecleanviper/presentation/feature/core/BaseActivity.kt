package com.gabriellferreira.basecleanviper.presentation.feature.core

import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabriellferreira.basecleanviper.presentation.feature.core.progressDialog.ProgressDialog
import javax.inject.Inject

abstract class BaseActivity<V, P> : AppCompatActivity(),
    BaseContract.View where V : BaseContract.View, P : BaseContract.Presenter<V> {

    @Inject
    lateinit var presenter: P

    @Inject
    lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var resourcesDagger: Resources

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            presenter.attachView(this as V)
        } catch (e: ClassCastException) {
            throw RuntimeException("Type V must be the same type of this class", e)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        if (progressDialog.showsDialog && !progressDialog.isAdded) {
            progressDialog.showNow(supportFragmentManager, ProgressDialog::class.java.canonicalName)
        }
    }

    override fun hideProgress() {
        if (progressDialog.isAdded) {
            progressDialog.dismiss()
        }
    }
}