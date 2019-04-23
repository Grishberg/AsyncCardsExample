package com.grishberg.cardscommon

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class CachedMeasureViewGroup @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    style: Int = 0
) : FrameLayout(ctx, attrs, style) {
    private var needToMeasure = false

    fun markNeedToMeasure() {
        needToMeasure = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (measuredWidth > 0 && measuredHeight > 0) {
            setMeasuredDimension(measuredWidth, measuredHeight)
            return
        }
        needToMeasure = false
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}