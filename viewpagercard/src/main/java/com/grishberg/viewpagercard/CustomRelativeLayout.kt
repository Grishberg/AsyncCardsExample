package com.grishberg.viewpagercard

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout

private const val TAG = "[DEBUG]"

class CustomRelativeLayout @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    style: Int = 0
) : RelativeLayout(ctx, attrs, style) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "CustomRelativeLayout: onMeasure thread = " + Thread.currentThread())
    }
}