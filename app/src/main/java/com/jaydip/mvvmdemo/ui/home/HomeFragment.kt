package com.jaydip.mvvmdemo.ui.home

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.jaydip.mvvmdemo.MainApplication
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.HomeFragmentBinding
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.ui.base.BaseFragment
import com.jaydip.mvvmdemo.ui.orderlist.OrderListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding,
        OrderListViewModel>(R.layout.fragment_home) {
    private var mHomeMenuPagerAdapter: HomeMenuPagerAdapter? = null
    private val navController: NavController by lazy {
        actMainNavHostContainer.findNavController()
    }

    //    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun initializeViews() {
        super.initializeViews()
        lifecycle.addObserver(viewModel)
        MainApplication.navController = navController
        frgHomeTvWelcome.text = getString(
            R.string.str_welcome,
            PrefModel.userName
        )
        initTabLayout()
    }
    private fun initTabLayout() {
        frgHomeViewPagerMenu.isPagingEnabled = false
        mHomeMenuPagerAdapter =
            HomeMenuPagerAdapter(this.childFragmentManager)
        frgHomeViewPagerMenu.adapter = mHomeMenuPagerAdapter
        frgHomeViewPagerMenu.offscreenPageLimit = 2
        frgHomeTabsMenu.addTab(frgHomeTabsMenu.newTab().setText(getString(R.string.str_menu_active)))
        frgHomeTabsMenu.addTab(frgHomeTabsMenu.newTab().setText(getString(R.string.str_menu_completed)))
        frgHomeTabsMenu.getTabAt(0)?.customView =
            mHomeMenuPagerAdapter!!.getTabView(0, requireContext())
        frgHomeTabsMenu.getTabAt(1)?.customView =
            mHomeMenuPagerAdapter!!.getTabView(1, requireContext())

        val tvTabName =
            frgHomeTabsMenu.getTabAt(0)!!.customView!!.findViewById(R.id.customTabTvTabName) as TextView
        tvTabName.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorTabSelected
            )
        )
        frgHomeTabsMenu.getTabAt(0)!!.select()

        frgHomeViewPagerMenu.currentItem=0
        frgHomeTabsMenu.tabGravity = (TabLayout.GRAVITY_FILL)
        mHomeMenuPagerAdapter!!.notifyDataSetChanged()


        frgHomeViewPagerMenu.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                frgHomeTabsMenu
            )
        )
        frgHomeTabsMenu.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                try {
                    frgHomeViewPagerMenu.currentItem = tab.position
                    val tvTabName =
                        tab.customView!!.findViewById(R.id.customTabTvTabName) as TextView
                    tvTabName.setTextColor(
                        ContextCompat.getColor(
                            context!!,
                            R.color.colorTabSelected
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                try {
                    val tvTabName =
                        tab!!.customView!!.findViewById(R.id.customTabTvTabName) as TextView
                    tvTabName.setTextColor(
                        ContextCompat.getColor(
                            context!!,
                            R.color.colorTabUnSelected
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

    override val viewModel: OrderListViewModel by viewModel()
}