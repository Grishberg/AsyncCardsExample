package com.grishberg.cardscommon

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.grishberg.cardscommon.rv.ChildItemAdapter

class SampleCard(
    private val ctx: Context,
    private val rootView: ViewGroup,
    private val adapter: ChildItemAdapter,
    private val asyncViewFabric: AsyncViewFabric
) : Card {
    private var viewPreparedListener: OnViewPreparedListener = OnViewPreparedListener.STUB
    private lateinit var rv: RecyclerView

    fun init() {
        rv = rootView.findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        rv.adapter = adapter
    }

    fun attachToParent(parent: ViewGroup) {
        parent.addView(rootView)
    }

    override fun onConfigurationChanged() {
        //TODO: re-measure child cards
    }

    override fun bindWithData(data: CardData) {
        asyncViewFabric.prepareViews(data.items)
        adapter.setData(data.items)
    }

    override fun setOnMeasuredListener(listener: OnViewPreparedListener) {
        viewPreparedListener = listener
    }
}