package com.example.carcrimesplus.rv.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carcrimesplus.rv.navdrawer.items.NavItem

interface RecyclerDelegate<VH: RecyclerView.ViewHolder, I: RecyclerItem> {

    fun onCreateViewHolder(parent: ViewGroup): VH

    fun onBindViewHolder(holder: @UnsafeVariance VH, item: @UnsafeVariance I, position: Int)

    fun isSuitable(item: RecyclerItem): Boolean
}
