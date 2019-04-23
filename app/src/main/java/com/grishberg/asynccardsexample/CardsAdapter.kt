package com.grishberg.asynccardsexample

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.grishberg.cardscommon.*
import com.grishberg.cardscommon.rv.ChildItemAdapter

private const val TAG = "[DEBUG]:CardsAdapter"

class CardsAdapter(
    private val itemLayoutResProvider: LayoutResProvider,
    private val stubResProvider: LayoutResProvider,
    private val cardDimensionProvider: CardDimensionProvider,
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<CardViewHolder>() {
    var items: List<CardData> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        Log.d(TAG, "onCreateViewHolder: $viewType")
        val context = parent.context
        val rootView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sliding_card_layout, parent, false) as ViewGroup

        val asyncViewFabric = AsyncViewFabric(context, itemLayoutResProvider, cardDimensionProvider)
        val childItemAdapter = ChildItemAdapter(asyncViewFabric, stubResProvider, inflater)

        val sampleCard = SampleCard(context, rootView, childItemAdapter, asyncViewFabric)
        sampleCard.init()
        return CardViewHolder(rootView, sampleCard)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(vh: CardViewHolder, pos: Int) {

        vh.bindWithData(items[pos])
    }
}