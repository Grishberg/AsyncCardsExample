package com.grishberg.viewpagercard

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log

private const val TAG = "[DEBUG]"

class CustomViewPager @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null
) : ViewPager(ctx, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (measuredWidth > 0 && measuredHeight > 0 && false) {
            setMeasuredDimension(measuredWidth, measuredHeight)
            Log.d(TAG, "CustomViewPager: onMeasure setDimension, thread = " + Thread.currentThread())
            return
        }
        Log.d(TAG, "CustomViewPager: onMeasure thread = " + Thread.currentThread())
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Thread.sleep(500)
    }
}