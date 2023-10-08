package com.example.carcrimesplus.navigation

import com.example.carcrimesplus.rv.content.items.Content

data class NavigationEvent(
    val eventId: Int,
    val title: String,
    val content: List<Content>
)