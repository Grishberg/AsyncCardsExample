package com.grishberg.cardscommon

import android.support.annotation.AnyThread

interface Card {
    fun onConfigurationChanged()

    @AnyThread
    fun bindWithData(data: CardData)

    fun setOnMeasuredListener(listener: OnViewPreparedListener)
}