package com.example.carcrimesplus.rv.navdrawer.items

import com.example.carcrimesplus.rv.content.items.Content

data class SubScreenNavItem(
    override val name: String,
    var selected: Boolean,
    val content: List<Content>
): NavItem