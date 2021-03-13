package com.path_studio.githubuser.Models

import android.content.res.Resources
import android.util.Log
import com.path_studio.githubuser.R

object UserData {

    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataEmail: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLink: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataAvatar: Array<String>
    private lateinit var dataStarred: Array<String>
    private lateinit var dataBackground: Array<String>

    private fun dataSource(resources: Resources){
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataEmail = resources.getStringArray(R.array.email)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
        dataLink = resources.getStringArray(R.array.link)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataAvatar = resources.getStringArray(R.array.avatar)
        dataStarred = resources.getStringArray(R.array.starred)
        dataBackground = resources.getStringArray(R.array.background)
    }

    fun getListUsers(resources: Resources): ArrayList<User> {
        dataSource(resources)

        val listUsers = ArrayList<User>()
        for (position in dataName.indices) {
            val user = User(
                    dataUsername[position],
                    dataName[position],
                    dataEmail[position],
                    dataLocation[position],
                    dataRepository[position].toInt(),
                    dataStarred[position].toInt(),
                    dataCompany[position],
                    dataLink[position],
                    dataFollower[position].toInt(),
                    dataFollowing[position].toInt(),
                    dataAvatar[position],
                    dataBackground[position]
            )
            listUsers.add(user)
        }
        return listUsers
    }

    fun getListUserByUsernameList(resources: Resources, selectedUsername: Array<String>): ArrayList<User>{
        dataSource(resources)

        val selectedUsers = ArrayList<User>()
        for (position in dataName.indices) {
            if(dataUsername[position] in selectedUsername){
                val user = User(
                    dataUsername[position],
                    dataName[position],
                    dataEmail[position],
                    dataLocation[position],
                    dataRepository[position].toInt(),
                    dataStarred[position].toInt(),
                    dataCompany[position],
                    dataLink[position],
                    dataFollower[position].toInt(),
                    dataFollowing[position].toInt(),
                    dataAvatar[position],
                    dataBackground[position]
                )
                selectedUsers.add(user)
            }
        }
        return selectedUsers
    }

    fun getUserByUsername(resources: Resources, username: String): ArrayList<User>{
        dataSource(resources)

        val selectedUsers = ArrayList<User>()
        for (position in dataName.indices) {
            if(dataUsername[position].equals(username)){
                val user = User(
                    dataUsername[position],
                    dataName[position],
                    dataEmail[position],
                    dataLocation[position],
                    dataRepository[position].toInt(),
                    dataStarred[position].toInt(),
                    dataCompany[position],
                    dataLink[position],
                    dataFollower[position].toInt(),
                    dataFollowing[position].toInt(),
                    dataAvatar[position],
                    dataBackground[position]
                )
                selectedUsers.add(user)
            }
        }
        return selectedUsers
    }
}