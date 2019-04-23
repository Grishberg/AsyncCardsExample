package com.grishberg.asynccardsexample

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.grishberg.cardscommon.CardData
import com.grishberg.cardscommon.SampleCard

class CardViewHolder(
    private val view: View,
    private val card: SampleCard
) : RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.title)
    private val description = view.findViewById<TextView>(R.id.description)
    private val rv = view.findViewById<RecyclerView>(R.id.rv)

    fun bindWithData(data: CardData) {
        title.text = data.title
        description.text = data.title
        card.bindWithData(data)
    }

}