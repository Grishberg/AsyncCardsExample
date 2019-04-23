package com.grishberg.cardscommon.rv

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.grishberg.cardscommon.CardData
import com.grishberg.cardscommon.R

private const val TAG = "[DEBUG]:ChildItemVH"

class ChildItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val content = view.findViewById<ViewGroup>(R.id.stubContainer)
    private lateinit var title: TextView
    private lateinit var description: TextView

    fun initWithRealView(viewByPosition: View, pos: Int) {
        Log.d(TAG, "initWithRealView ${viewByPosition.tag}")
        viewByPosition.tag = pos
        content.addView(viewByPosition)
        title = content.findViewById(R.id.title)
        description = content.findViewById(R.id.description)
        isStub = false
    }

    fun bindWithData(data: CardData) {
        title.text = data.title
        description.text = data.description
    }

    var isStub: Boolean = true

}