package com.example.carcrimesplus.rv.navdrawer.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.carcrimesplus.R
import com.example.carcrimesplus.rv.base.RecyclerDelegate
import com.example.carcrimesplus.rv.navdrawer.VH.GroupViewHolder
import com.example.carcrimesplus.rv.base.RecyclerItem
import com.example.carcrimesplus.rv.navdrawer.items.GroupNavItem
import com.example.carcrimesplus.rv.navdrawer.items.SubScreenNavItem

fun interface GroupClickListener {
    fun groupClicked(shouldExpand: Boolean, position: Int, subItems: List<SubScreenNavItem>)
}

class GroupDelegate(
    private val groupClickListener: GroupClickListener,
): RecyclerDelegate<GroupViewHolder, GroupNavItem> {

    override fun onCreateViewHolder(parent: ViewGroup): GroupViewHolder {
        return GroupViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, item: GroupNavItem, position: Int) {
        holder.binding.apply {
            name.text = item.name
            if (item.selected) {
                name.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_remove), null)
            } else {
                name.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_add), null)
            }

            holder.itemView.setOnClickListener {
                groupClickListener.groupClicked(!item.selected, position, item.items)
            }
        }
    }

    override fun isSuitable(item: RecyclerItem): Boolean {
        return item is GroupNavItem
    }
}