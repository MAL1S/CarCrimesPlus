package com.example.carcrimesplus.rv.navdrawer.delegate

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.carcrimesplus.R
import com.example.carcrimesplus.navigation.NavigationEvent
import com.example.carcrimesplus.navigation.coroutineScope
import com.example.carcrimesplus.navigation.navigationObserver
import com.example.carcrimesplus.rv.navdrawer.VH.SubScreenViewHolder
import com.example.carcrimesplus.rv.base.RecyclerDelegate
import com.example.carcrimesplus.rv.base.RecyclerItem
import com.example.carcrimesplus.rv.navdrawer.items.NavItem
import com.example.carcrimesplus.rv.navdrawer.items.SubScreenNavItem
import kotlinx.coroutines.launch

class SubScreenItemDelegate(
    private val screenClickListener: ScreenClickListener
): RecyclerDelegate<SubScreenViewHolder, SubScreenNavItem> {

    override fun onCreateViewHolder(parent: ViewGroup): SubScreenViewHolder {
        return SubScreenViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_screen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubScreenViewHolder, item: SubScreenNavItem, position: Int) {
        holder.binding.apply {
            name.text = item.name
            name.setTextColor(
                if (item.selected) {
                    ContextCompat.getColor(holder.itemView.context, R.color.blue)
                } else {
                    ContextCompat.getColor(holder.itemView.context, R.color.gray)
                }
            )
            name.setTypeface(null, if (item.selected) Typeface.BOLD else Typeface.NORMAL)
        }

        holder.itemView.setOnClickListener {
            screenClickListener.screenClicked(position)

            coroutineScope.launch {
                navigationObserver.sendNavigationEvent(NavigationEvent(position, item.name, item.content))
            }
        }
    }

    override fun isSuitable(item: RecyclerItem): Boolean {
        return item is SubScreenNavItem
    }
}