package com.jaydip.mvvmdemo.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.jaydip.mvvmdemo.util.NetworkConnectivity
import timber.log.Timber

/**
 * Created by Mayur Solanki on 29/09/20, 6:39 PM.
 */
abstract class BaseActivity<BINDING : ViewDataBinding, VIEWMODEL :
Any>(@LayoutRes val layoutId: Int) : AppCompatActivity() {
    private var isConnected: Boolean = false
    private lateinit var binding: BINDING
    protected abstract val viewModel: VIEWMODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDataBinding()
        initializeViews()
//        checkNetworkConnectivity()
    }

    /**
     * Init Views
     */
    open fun initializeViews() {}

    private fun initializeDataBinding() {
        DataBindingUtil.setContentView<BINDING>(this, layoutId)
            .apply {
                binding = this
                lifecycleOwner = this@BaseActivity
                setVariable(1, viewModel)
                executePendingBindings()
            }
    }

    /**
     * Display Toast
     * @param message String
     * @param duration Int
     */
    fun toast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
//        this.toastLong(message,duration)
    }

    /**
     * Display Toast
     * @param message Int : String resource ID
     */
    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }

    /**
     * Has Internet
     * @return Boolean
     */
    fun hasInternet(): Boolean {
        return isConnected
    }

    private fun checkNetworkConnectivity() {
        NetworkConnectivity.observe(this, Observer {
            if (it) {
                isConnected = true
                Timber.d(" Network Connected")
            } else {
                isConnected = true
                Timber.d(" Network Connectivity Lost")
            }
        })
    }

}