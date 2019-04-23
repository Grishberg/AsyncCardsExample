package com.grishberg.horizontalcards

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

class HorizontalSlidesAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<HorizontalViewHolder>() {
    var horizontalData: List<SlideCardData> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): HorizontalViewHolder {
        val view = inflater.inflate(R.layout.slide_item_layout, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun getItemCount(): Int = horizontalData.size

    override fun onBindViewHolder(vh: HorizontalViewHolder, pos: Int) {
        vh.bind(horizontalData[pos])
    }
}