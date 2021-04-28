package com.jaydip.mvvmdemo.ui.help

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jaydip.mvvmdemo.MainApplication
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.HelpFragmentBinding
import com.jaydip.mvvmdemo.ui.base.BaseFragment
import com.jaydip.mvvmdemo.util.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class HelpFragment : BaseFragment<HelpFragmentBinding, HelpViewModel>(R.layout.fragment_help) {

    private val navController: NavController by lazy {
        actMainNavHostContainer.findNavController()
    }
//    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun initializeViews() {
        super.initializeViews()
        lifecycle.addObserver(viewModel)
        MainApplication.navController = navController
        initAppointmentList()
        setupObserver()
        if (isNetworkAvailable(requireContext())) {
        } else {
            toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
        }

    }



    private fun initAppointmentList() {
//        recentAppointmentListAdapter = RecentAppointmentListAdapter(this, appointmentList)
//        frgHomeRvRecentAppointment.layoutManager =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
//        frgHomeRvRecentAppointment.adapter = recentAppointmentListAdapter
////        recentAppointmentListAdapter.setAppointmentList(appointmentList)
        checkAppointmentIsEmpty()
    }

    private fun setupObserver() {

    }

    private fun checkAppointmentIsEmpty() {
//        if (appointmentList.isNotEmpty()) {
//            if (appointmentList.size < 3) {
//                frgHomeTvViewAll.visibility = View.GONE
//            } else {
//                frgHomeTvViewAll.visibility = View.VISIBLE
//            }
//            frgHomeTvRecentAppointments.visibility = View.VISIBLE
//            frgHomeRvRecentAppointment.visibility = View.VISIBLE
//        } else {
//            frgHomeTvRecentAppointments.visibility = View.GONE
//            frgHomeTvViewAll.visibility = View.GONE
//            frgHomeRvRecentAppointment.visibility = View.GONE
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

    override val viewModel: HelpViewModel by viewModel()
}