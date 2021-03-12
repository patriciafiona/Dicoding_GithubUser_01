package com.path_studio.githubuser.Models

import android.content.res.Resources
import com.path_studio.githubuser.R

object UserData {
    fun getListUsers(resources: Resources): ArrayList<User> {
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataLink = resources.getStringArray(R.array.link)
        val dataFollower = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.getStringArray(R.array.avatar)
        val dataStarred = resources.getStringArray(R.array.starred)

        val listUsers = ArrayList<User>()
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataLocation[position],
                dataRepository[position].toInt(),
                dataStarred[position].toInt(),
                dataCompany[position],
                dataLink[position],
                dataFollower[position].toInt(),
                dataFollowing[position].toInt(),
                dataAvatar[position]
            )
            listUsers.add(user)
        }
        return listUsers
    }
}