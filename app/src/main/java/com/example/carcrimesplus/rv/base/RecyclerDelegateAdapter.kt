package com.example.carcrimesplus.rv.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

class RecyclerDelegateAdapter(
    private val delegates: MutableList<RecyclerDelegate<out RecyclerView.ViewHolder, out RecyclerItem>>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<RecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        delegates.forEach {
            if (it.isSuitable(item)) {
                it.onBindViewHolder(holder, item, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        delegates.forEachIndexed { index, delegate ->
            if (delegate.isSuitable(item)) {
                return index
            }
        }
        throw IllegalStateException("Your delegate must correctly implement 'isSuitable()' function")
    }

    override fun getItemCount(): Int = items.size

    fun addDelegate(delegate: RecyclerDelegate<out RecyclerView.ViewHolder, out RecyclerItem>) {
        delegates.add(delegate)
    }
}