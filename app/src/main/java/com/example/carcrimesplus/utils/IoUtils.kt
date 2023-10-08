package com.example.carcrimesplus.utils

import android.database.Cursor
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.nio.charset.Charset


object IOUtils {
    private const val BUFFER_SIZE = 2048

    fun toByteArray(inputStream: InputStream?): ByteArray? {
        if (inputStream == null) {
            return null
        }
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(BUFFER_SIZE)
        var read: Int
        while (inputStream.read(buffer).also { read = it } > 0) {
            outputStream.write(buffer, 0, read)
        }
        return outputStream.toByteArray()
    }

    fun toString(inputStream: InputStream): String {
        val buffer = CharArray(BUFFER_SIZE)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val builder = StringBuilder(inputStream.available())
        var read: Int
        while (reader.read(buffer).also { read = it } != -1) {
            builder.append(buffer, 0, read)
        }
        return builder.toString()
    }
}