package com.path_studio.githubuser.Models

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.path_studio.githubuser.Utils

object RepoData {
    fun getRepoList(context: Context): ArrayList<Repository>{
        val jsonFileString = Utils.getJsonDataFromAsset(
            context.applicationContext,
            "repositories.json"
        )
        if (jsonFileString != null) {
            //Log.i("data", jsonFileString)
        }

        val gson = Gson()
        val listRepositoryType = object : TypeToken<ArrayList<Repository>>() {}.type

        return gson.fromJson(jsonFileString, listRepositoryType)
    }
}