package com.grishberg.horizontalcards

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout.HORIZONTAL

class SlidesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    styles: Int = 0
) : FrameLayout(context, attrs, styles) {

    private val recyclerView: RecyclerView
    private val adapter: HorizontalSlidesAdapter
    private var forceMeasure = false

    init {
        LayoutInflater.from(context).inflate(R.layout.slides_layout, this, true)
        recyclerView = findViewById(R.id.slidesRecyclerView)
        adapter = HorizontalSlidesAdapter(LayoutInflater.from(context))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

    fun bindWithData(items: List<SlideCardData>) {
        adapter.horizontalData = items
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!forceMeasure && measuredWidth > 0 && measuredHeight > 0) {
            setMeasuredDimension(measuredWidth, measuredHeight)
            return
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}