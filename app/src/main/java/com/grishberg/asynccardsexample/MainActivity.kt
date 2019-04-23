package com.grishberg.asynccardsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.grishberg.cardscommon.*
import com.grishberg.cardscommon.rv.ChildItemAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val feedRv = findViewById<RecyclerView>(R.id.feed)
        val inflater = LayoutInflater.from(this)
        //val viewHolderFabric = ViewHolderFabric(inflater)
        //val lm = LinearLayoutManager(this, VERTICAL, false)
        //val feed = Feed(feedRv, adap

        val content = findViewById<ViewGroup>(R.id.container)
        val itemLayoutResProvider = object : LayoutResProvider {
            override fun provideLayoutRes(position: Int): Int = R.layout.child_item_layout
        }
        val cardDimensionProvider = object : CardDimensionProvider {
            override val widthMeasureSpec: Int
                get() = this@MainActivity.resources.getDimensionPixelSize(R.dimen.childCardWidth)
            override val heightMeasureSpec: Int
                get() = this@MainActivity.resources.getDimensionPixelSize(R.dimen.childCardHeight)
        }
        val stubResProvider = object : LayoutResProvider {
            override fun provideLayoutRes(position: Int): Int = R.layout.stub_view_layout
        }

        val adapter = CardsAdapter(itemLayoutResProvider, stubResProvider, cardDimensionProvider, inflater)
        val rv = findViewById<RecyclerView>(R.id.parentRv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.items = createData()

        /*
        val asyncViewFabric = AsyncViewFabric(this, itemLayoutResProvider, cardDimensionProvider)
        val childItemAdapter = ChildItemAdapter(asyncViewFabric, stubResProvider, inflater)
        val parent = FrameLayout(this)
        val rootView = inflater.inflate(R.layout.sliding_card_layout, parent, false) as ViewGroup

        val sampleCard = SampleCard(this, rootView, childItemAdapter, asyncViewFabric)

        val data = createData()
        sampleCard.init()
        sampleCard.bindWithData(data[0])
        sampleCard.attachToParent(content)
        */
    }

    private fun createData(): List<CardData> {
        val data = ArrayList<CardData>()
        for (i in 0..10) {
            data.add(CardData("title $i", "description $i", createSubItemData()))
        }
        return data
    }

    private fun createSubItemData(): List<CardData> {
        val data = ArrayList<CardData>()
        for (i in 0..10) {
            data.add(CardData("sub item title $i", "sub item description $i"))
        }
        return data
    }
}
