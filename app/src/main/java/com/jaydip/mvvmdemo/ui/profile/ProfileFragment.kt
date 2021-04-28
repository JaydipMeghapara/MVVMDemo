package com.jaydip.mvvmdemo.ui.profile

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jaydip.mvvmdemo.MainApplication
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.ProfileFragmentBinding
import com.jaydip.mvvmdemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileFragmentBinding, ProfileViewModel>(R.layout.fragment_profile) {

    private val navController: NavController by lazy {
        actMainNavHostContainer.findNavController()
    }

    //    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun initializeViews() {
        super.initializeViews()
        lifecycle.addObserver(viewModel)
        MainApplication.navController = navController
        setupUI()
        setupObserver()
//        if (isNetworkAvailable(requireContext())) {
//        } else {
//            toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
//        }

    }

    private fun setupUI() {
    }


    private fun setupObserver() {

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

    override val viewModel: ProfileViewModel by viewModel()

}