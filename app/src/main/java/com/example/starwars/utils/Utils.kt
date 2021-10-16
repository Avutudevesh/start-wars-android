package com.example.starwars.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException

object Utils {

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url).into(this)
    }

}