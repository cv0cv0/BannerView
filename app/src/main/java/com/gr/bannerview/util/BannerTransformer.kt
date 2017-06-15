package com.gr.bannerview.util

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by gr on 2017/6/15.
 */

class BannerTransformer : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.9f

    override fun transformPage(page: View, position: Float) {
        when (position) {
            in -1..1 -> page.scaleY = Math.max(MIN_SCALE, 1 - Math.abs(position))
            else -> page.scaleY = MIN_SCALE
        }
    }
}