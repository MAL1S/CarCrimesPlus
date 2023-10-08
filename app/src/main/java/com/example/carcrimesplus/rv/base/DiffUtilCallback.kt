package com.example.carcrimesplus.rv.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carcrimesplus.rv.navdrawer.items.NavItem

class DiffUtilCallback(
    private val oldItems: List<NavItem>,
    private val newItems: List<NavItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].name == newItems[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].name == newItems[newItemPosition].name
}

fun RecyclerView.Adapter<RecyclerView.ViewHolder>.updateDiffUtil(newList: List<NavItem>, oldList: List<NavItem>) {
    val diffUtilCallback = DiffUtilCallback(oldList, newList)
    val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
    diffResult.dispatchUpdatesTo(this)
}
