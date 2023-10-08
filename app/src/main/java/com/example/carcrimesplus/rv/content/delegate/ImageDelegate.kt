package com.example.carcrimesplus.rv.content.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.carcrimesplus.R
import com.example.carcrimesplus.rv.base.RecyclerDelegate
import com.example.carcrimesplus.rv.base.RecyclerItem
import com.example.carcrimesplus.rv.content.VH.ImageViewHolder
import com.example.carcrimesplus.rv.content.VH.TextViewHolder
import com.example.carcrimesplus.rv.content.items.ImageContent
import com.example.carcrimesplus.rv.content.items.TextContent

class ImageDelegate: RecyclerDelegate<ImageViewHolder, ImageContent> {

    override fun onCreateViewHolder(parent: ViewGroup): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, item: ImageContent, position: Int) {
        holder.binding.apply {
            img.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, item.imageDrawableId))
        }
    }

    override fun isSuitable(item: RecyclerItem): Boolean {
        return item is ImageContent
    }
}