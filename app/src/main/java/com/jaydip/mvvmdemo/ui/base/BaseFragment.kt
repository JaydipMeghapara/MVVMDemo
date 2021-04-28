package com.jaydip.mvvmdemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jaydip.mvvmdemo.util.NetworkConnectivity
import timber.log.Timber


abstract class BaseFragment<VIEWDATABINDING : ViewDataBinding,
        VIEWMODEL : Any>(@LayoutRes val layoutId: Int) : Fragment() {
    protected abstract val viewModel: VIEWMODEL
    protected lateinit var binding: VIEWDATABINDING
    private var isConnected: Boolean = false

    companion object {
        var isGlobalSearchCreated: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initializeDataBinding(inflater, container).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyViewFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
//        checkNetworkConnectivity()
    }

    /**
     * Init Views
     */
    open fun initializeViews() {
    }

    open fun onDestroyViewFragment() {
    }

    private fun initializeDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VIEWDATABINDING =
        DataBindingUtil.inflate<VIEWDATABINDING>(
            inflater,
            layoutId,
            container,
            false
        ).apply {
            binding = this
            lifecycleOwner = this@BaseFragment
            setVariable(1, viewModel)
            executePendingBindings()
        }

    /**
     * Display Toast
     * @param message String
     * @param duration Int
     */
    fun toast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(activity, message, duration).show()
//        requireActivity().toastLong(message,duration)
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
        NetworkConnectivity.observe(viewLifecycleOwner, Observer {
            if (it) {
                isConnected = true
                Timber.d(" Base Fragment Network Connected")
            } else {
                isConnected = true
                Timber.d(" Base Fragment Network Connectivity Lost")
            }
        })
    }


}
