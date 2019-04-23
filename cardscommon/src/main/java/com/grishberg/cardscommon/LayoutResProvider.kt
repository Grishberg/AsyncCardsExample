package com.grishberg.cardscommon

import android.support.annotation.LayoutRes

/**
 * Provides layout res id for position.
 */
interface LayoutResProvider {
    @LayoutRes
    fun provideLayoutRes(position: Int): Int
}