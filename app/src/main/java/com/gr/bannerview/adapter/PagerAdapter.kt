package com.gr.bannerview.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by gr on 2017/6/14.
 */
class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var items=ArrayList<Fragment>()

    fun addFragment(fragment: Fragment){
        items.add(fragment)
    }

    override fun getItem(p0: Int): Fragment {
        return items[p0]
    }

    override fun getCount(): Int {
        return items.size
    }
}