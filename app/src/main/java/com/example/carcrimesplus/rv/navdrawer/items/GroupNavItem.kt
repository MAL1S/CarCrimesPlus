package com.example.carcrimesplus.rv.navdrawer.items

data class GroupNavItem(
    override val name: String,
    val items: List<SubScreenNavItem>,
    var selected: Boolean = false
): NavItem