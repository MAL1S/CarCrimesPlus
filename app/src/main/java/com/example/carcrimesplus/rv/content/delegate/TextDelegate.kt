package com.example.carcrimesplus.rv.content.delegate

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.example.carcrimesplus.R
import com.example.carcrimesplus.rv.base.RecyclerDelegate
import com.example.carcrimesplus.rv.base.RecyclerItem
import com.example.carcrimesplus.rv.content.VH.TextViewHolder
import com.example.carcrimesplus.rv.content.items.TextContent

class TextDelegate: RecyclerDelegate<TextViewHolder, TextContent> {

    override fun onCreateViewHolder(parent: ViewGroup): TextViewHolder {
        return TextViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_item_text, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TextViewHolder, item: TextContent, position: Int) {
        holder.binding.apply {
            tvText.text = HtmlCompat.fromHtml(item.text, HtmlCompat.FROM_HTML_MODE_LEGACY)
            tvText.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun isSuitable(item: RecyclerItem): Boolean {
        return item is TextContent
    }
}