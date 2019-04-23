package com.grishberg.cardscommon

import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import java.lang.ref.WeakReference

class AsyncViewFabric(
    private val context: Context,
    private val layoutResProvider: LayoutResProvider,
    private val cardDimensionProvider: CardDimensionProvider
) {
    var viewPreparedListener: OnViewPreparedListener = OnViewPreparedListener.STUB
    private val listener = ViewPreparedListener()
    private val viewCache = ArrayList<View>()
    private val parent = FrameLayout(context)

    fun prepareViews(items: List<CardData>) {
        val task = PrepareViewTask(
            items,
            WeakReference(LayoutInflater.from(context)),
            WeakReference(parent),
            WeakReference(layoutResProvider),
            WeakReference(cardDimensionProvider),
            WeakReference(listener)
        )
        task.execute()
    }

    fun hasViewByPosition(position: Int): Boolean = viewCache.size > position

    fun getViewByPosition(position: Int): View = viewCache[position]

    private inner class ViewPreparedListener : OnViewPreparedListener {
        override fun onViewPrepared(position: Int, view: View) {
            viewCache.add(view)
            viewPreparedListener.onViewPrepared(position, view)
        }
    }

    private class PrepareViewTask(
        val items: List<CardData>,
        val inflaterRef: WeakReference<LayoutInflater>,
        val parenRef: WeakReference<ViewGroup>,
        val layoutResProviderRef: WeakReference<LayoutResProvider>,
        val dimensionProviderRef: WeakReference<CardDimensionProvider>,
        var viewPreparedListenerRef: WeakReference<OnViewPreparedListener>
    ) : AsyncTask<Void, Pair<Int, View>, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            for (i in 0 until items.size) {
                val inflater = inflaterRef.get()
                val parent = parenRef.get()
                val layoutResProvider = layoutResProviderRef.get()
                val dimensionProvider = dimensionProviderRef.get()

                if (inflater != null && parent != null && layoutResProvider != null && dimensionProvider != null) {
                    val view = inflater.inflate(layoutResProvider.provideLayoutRes(i), parent, false)
                    view.measure(dimensionProvider.widthMeasureSpec, dimensionProvider.heightMeasureSpec)
                    publishProgress(Pair(i, view))
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Pair<Int, View>) {
            val viewPrepareListener = viewPreparedListenerRef.get()
            if (viewPrepareListener == null || values.isEmpty() || isCancelled) {
                return
            }
            viewPrepareListener.onViewPrepared(values[0].first, values[0].second)
        }
    }
}