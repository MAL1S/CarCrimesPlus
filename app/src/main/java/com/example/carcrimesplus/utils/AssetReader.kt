package com.example.carcrimesplus.utils

import android.content.Context

class AssetReader(private val context: Context) {

    fun read(filename: String): String {
        return IOUtils.toString(context.assets.open(filename))
    }
}