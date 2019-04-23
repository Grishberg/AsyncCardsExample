package com.grishberg.cardscommon

import android.view.View

interface OnViewPreparedListener {
    fun onViewPrepared(position: Int, view: View)

    object STUB : OnViewPreparedListener {
        override fun onViewPrepared(position: Int, view: View) {
            /* stub */
        }
    }
}