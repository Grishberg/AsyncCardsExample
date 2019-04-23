package com.grishberg.cardscommon.rv

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grishberg.cardscommon.AsyncViewFabric
import com.grishberg.cardscommon.CardData
import com.grishberg.cardscommon.LayoutResProvider
import com.grishberg.cardscommon.OnViewPreparedListener

private const val TAG = "[DEBUG]:ChildItemAd"

class ChildItemAdapter(
    private val asyncViewFabric: AsyncViewFabric,
    private val stubLayoutResProvider: LayoutResProvider,
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<ChildItemViewHolder>(), OnViewPreparedListener {

    private val items = ArrayList<CardData>()

    init {
        asyncViewFabric.viewPreparedListener = this
    }

    fun setData(newItems: List<CardData>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemViewHolder {
        val view = inflater.inflate(stubLayoutResProvider.provideLayoutRes(viewType), parent, false)
        return ChildItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ChildItemViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder pos = $position, isStub = ${holder.isStub}")
        if (holder.isStub) {
            if (asyncViewFabric.hasViewByPosition(position)) {
                holder.initWithRealView(asyncViewFabric.getViewByPosition(position), position)
                holder.bindWithData(items[position])
            }
        } else {
            holder.bindWithData(items[position])
        }
    }

    override fun onViewPrepared(position: Int, view: View) {
        notifyItemChanged(position)
    }
}