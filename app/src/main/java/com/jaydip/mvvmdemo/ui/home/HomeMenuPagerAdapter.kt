package com.jaydip.mvvmdemo.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.ui.orderlist.OrderListFragment

class HomeMenuPagerAdapter(
    manager: FragmentManager
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            OrderListFragment(false)
        } else OrderListFragment(true)

    }

    override fun getCount(): Int {
        return 2
    }

    @SuppressLint("InflateParams")
    fun getTabView(position: Int, context: Context): View? {
        val tab: View = LayoutInflater.from(context).inflate(R.layout.custom_tab_home, null)
        val tv = tab.findViewById(R.id.customTabTvTabName) as TextView
        if (position == 0) {
            tv.text =
                context.getString(R.string.str_menu_active)
        } else {
            tv.text =
                context.getString(R.string.str_menu_completed)
        }
        return tab
    }

}
