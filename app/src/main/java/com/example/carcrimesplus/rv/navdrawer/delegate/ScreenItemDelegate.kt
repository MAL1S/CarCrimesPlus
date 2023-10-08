package com.example.carcrimesplus.rv.navdrawer.delegate

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.carcrimesplus.R
import com.example.carcrimesplus.navigation.NavigationEvent
import com.example.carcrimesplus.navigation.coroutineScope
import com.example.carcrimesplus.navigation.navigationObserver
import com.example.carcrimesplus.rv.base.RecyclerDelegate
import com.example.carcrimesplus.rv.navdrawer.VH.ScreenViewHolder
import com.example.carcrimesplus.rv.base.RecyclerItem
import com.example.carcrimesplus.rv.navdrawer.items.NavItem
import com.example.carcrimesplus.rv.navdrawer.items.ScreenNavItem
import com.example.carcrimesplus.rv.navdrawer.items.SubScreenNavItem
import kotlinx.coroutines.launch

fun interface ScreenClickListener {
    fun screenClicked(position: Int)
}

class ScreenItemDelegate(
    private val screenClickListener: ScreenClickListener
): RecyclerDelegate<ScreenViewHolder, ScreenNavItem> {

    override fun onCreateViewHolder(parent: ViewGroup): ScreenViewHolder {
        return ScreenViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_screen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, item: ScreenNavItem, position: Int) {
        holder.binding.apply {
            name.text = item.name
            name.setTextColor(
                if (item.selected) {
                    ContextCompat.getColor(holder.itemView.context, R.color.blue)
                } else {
                    ContextCompat.getColor(holder.itemView.context, R.color.black)
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
        return item is ScreenNavItem
    }
}