package com.path_studio.githubuser

import android.content.Context
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

    fun convertNumberFormat(num: Int): String{
        var temp: String = ""
        if(num >= 1000){
            temp = String.format("%.1f", num.toDouble()/1000.0) + " K"
        }else if (num >= 1000000){
            temp = String.format("%.1f", num.toDouble()/1000000.0) + " M"
        }else{
            temp = num.toString()
        }
        return temp
    }

}