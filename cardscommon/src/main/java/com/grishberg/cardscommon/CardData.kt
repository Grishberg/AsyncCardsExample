package com.grishberg.cardscommon

data class CardData(
    val title: String,
    val description: String,
    val items: List<CardData> = ArrayList()
)