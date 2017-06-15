package com.gr.bannerview

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.gr.bannerview.adapter.PagerAdapter
import com.gr.bannerview.fragment.PagerFragment
import com.gr.bannerview.util.BannerTransformer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private val timer = Timer()
private var currentItem = 0
private var isStart = true

class MainActivity : AppCompatActivity() {
    private val adapter = PagerAdapter(supportFragmentManager)
    private var bannerTask:BannerTask?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = resources
        val packageName = packageName
        (1..7).map { res.getIdentifier("bing00$it", "drawable", packageName) }
                .forEach { adapter.addFragment(PagerFragment(it)) }
        viewPager.adapter = adapter
        viewPager.setPageTransformer(false, BannerTransformer())
        viewPager.offscreenPageLimit = 2
        viewPager.currentItem= currentItem
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                currentItem = p0
            }
        })

        button.text=if (isStart) "停止" else "开始"
        button.setOnClickListener {
            if (isStart) {
                bannerTask?.cancel()
                isStart = false
                button.text = "开始"
            } else {
                startBanner()
                isStart = true
                button.text = "停止"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isStart) startBanner()
    }

    override fun onPause() {
        super.onPause()
        bannerTask?.cancel()
    }

    private fun startBanner() {
        bannerTask=BannerTask(viewPager,adapter.count)
        timer.schedule(bannerTask, 2500, 2500)
    }

    private class BannerTask(val viewPager: ViewPager, val count:Int) : TimerTask() {

        override fun run() {
            currentItem++
            if (currentItem == count) currentItem = 0
            viewPager.post {
                viewPager.currentItem = currentItem
            }
        }
    }
}
