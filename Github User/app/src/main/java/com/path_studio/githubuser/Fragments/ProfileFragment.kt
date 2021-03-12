package com.path_studio.githubuser.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Adapters.ListPopularRepoAdapter
import com.path_studio.githubuser.Models.Repository
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.Models.UserData
import com.path_studio.githubuser.R
import com.path_studio.githubuser.Utils.getJsonDataFromAsset
import org.w3c.dom.Text

class ProfileFragment : Fragment() {

    private val MY_POSITION: Int = 10
    private var MY_USERNAME: String = ""
    private var MY_AVATAR: String = ""
    private lateinit var listUsers: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        //show my Github detail
        showDetails(rootView)

        //show popular repo using horizontal recycle view
        showPopularRepo(rootView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Show Search Bar
        (activity as MainActivity).setSearchBarVisibility(0)
    }

    private fun showDetails(view: View){
        val myName:TextView = view.findViewById(R.id.myName)
        val myAvatar: ImageView = view.findViewById(R.id.myAvatar)
        val myUsername: TextView = view.findViewById(R.id.myUsername)
        val myCompany: TextView = view.findViewById(R.id.myCompany)
        val myLink: TextView = view.findViewById(R.id.myLink)
        val myLocation: TextView = view.findViewById(R.id.myLocation)
        val myFollowers: TextView = view.findViewById(R.id.myFollowers)
        val myFollowings: TextView  = view.findViewById(R.id.myFollowings)
        val myRepository: TextView = view.findViewById(R.id.myRepository)
        val myStarred: TextView = view.findViewById(R.id.myStarred)

        //show data from arraylist
        listUsers = UserData.getListUsers(resources)
        val my_data = listUsers[MY_POSITION]

        myName.text = my_data.name
        MY_USERNAME = my_data.username.toString()
        myUsername.text = MY_USERNAME
        myCompany.text = my_data.company
        myLocation.text = my_data.location
        myLink.text = my_data.link
        myFollowers.text = my_data.followers.toString()
        myFollowings.text = my_data.following.toString()
        myRepository.text = my_data.repository.toString()
        myStarred.text = my_data.starred.toString()

        MY_AVATAR = my_data.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        myAvatar.setImageResource((activity as MainActivity).resources.getIdentifier(MY_AVATAR, "drawable", (activity as MainActivity).packageName))

    }

    private fun showPopularRepo(view: View){
        val rvPopularRepo: RecyclerView = view.findViewById(R.id.rv_popular_repo)
        rvPopularRepo.setHasFixedSize(true)

        val filterResult: ArrayList<Repository> = ArrayList<Repository>(getRepoList().filter { it.username == MY_USERNAME })
        showRecyclerList(rvPopularRepo, filterResult)
    }

    private fun showRecyclerList(rvApp: RecyclerView, list: ArrayList<Repository>) {
        rvApp.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val listAppAdapter = ListPopularRepoAdapter(list, activity as MainActivity, MY_AVATAR)
        rvApp.adapter = listAppAdapter
    }

    private  fun getRepoList(): ArrayList<Repository>{
        val jsonFileString = getJsonDataFromAsset((activity as MainActivity).applicationContext, "repositories.json")
        if (jsonFileString != null) {
            Log.i("data", jsonFileString)
        }

        val gson = Gson()
        val listRepositoryType = object : TypeToken<ArrayList<Repository>>() {}.type

        return gson.fromJson(jsonFileString, listRepositoryType)
    }

}