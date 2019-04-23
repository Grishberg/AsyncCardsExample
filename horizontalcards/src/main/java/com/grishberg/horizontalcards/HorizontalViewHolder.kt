package com.grishberg.horizontalcards

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class HorizontalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.title)
    val description = view.findViewById<TextView>(R.id.description)

    fun bind(slidesCard: SlideCardData) {
        title.text = slidesCard.title
        description.text = slidesCard.description
    }

}