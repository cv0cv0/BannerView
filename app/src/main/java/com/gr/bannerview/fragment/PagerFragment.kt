package com.gr.bannerview.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.gr.bannerview.R

/**
 * Created by gr on 2017/6/14.
 */
class PagerFragment(val resId: Int) : Fragment() {
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        imageView = inflater?.inflate(R.layout.fragment_pager, container, false) as ImageView
        imageView.post { setImage() }
        return imageView
    }

    private fun setImage() {
        val res = resources
        val opts = BitmapFactory.Options()
        opts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeResource(res, resId, opts)
        val sampleWidth = Math.ceil(opts.outWidth / imageView.width.toDouble()).toInt()
        val sampleHeight = Math.ceil(opts.outHeight / imageView.height.toDouble()).toInt()
        opts.inSampleSize = Math.max(sampleWidth, sampleHeight)
        opts.inJustDecodeBounds = false
        opts.inPreferredConfig=Bitmap.Config.RGB_565
        bitmap = BitmapFactory.decodeResource(res, resId, opts)
        imageView.setImageBitmap(bitmap)
    }
}