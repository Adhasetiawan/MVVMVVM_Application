package com.example.mvvmapplication.util

import android.view.View
import okhttp3.ResponseBody
import org.json.JSONObject

object utils {

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody?.string())
            jsonObject.getString("error")
        } catch (e: Exception) {
            e.message.toString()
        }
    }


    fun View.visible(){
        this.visibility = View.VISIBLE
    }
    fun View.gone(){
        this.visibility = View.GONE
    }
}