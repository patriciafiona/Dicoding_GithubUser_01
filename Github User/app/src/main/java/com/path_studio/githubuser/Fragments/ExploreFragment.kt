package com.path_studio.githubuser.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Adapters.ListDiscoverHorizontalAdapter
import com.path_studio.githubuser.Adapters.ListUserVerticalAdapter
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.Models.UserData
import com.path_studio.githubuser.R

class ExploreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_explore, container, false)

        //setting recycle view
        setRecycleView(rootView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Show Search Bar
        (activity as MainActivity).setSearchBarVisibility(1)
        (activity as MainActivity).clearSearchBar()
    }

    private fun setRecycleView(view: View){
        //show list of discover people
        val rvDiscover: RecyclerView = view.findViewById(R.id.rv_discover)
        rvDiscover.setHasFixedSize(true)

        //get 5 random user from all user
        val randomUsers: Array<String> = resources.getStringArray(R.array.username)
        randomUsers.shuffle()
        showRecyclerList(
            rvDiscover, UserData.getListUserByUsernameList(
                resources, randomUsers.take(
                    5
                ).toTypedArray()
            ), 0
        )

        //show list of apps
        val rvUser: RecyclerView = view.findViewById(R.id.rv_users)
        rvUser.setHasFixedSize(true)

        showRecyclerList(rvUser, UserData.getListUsers(resources), 1)
    }

    private fun showRecyclerList(
        recyclerView: RecyclerView,
        list: ArrayList<User>,
        statusVertical: Int
    ) {
        if(statusVertical == 1){
            recyclerView.layoutManager = LinearLayoutManager(activity)
            val listAppAdapter = ListUserVerticalAdapter(list, activity as MainActivity)
            recyclerView.adapter = listAppAdapter
        }else{
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val listAppAdapter = ListDiscoverHorizontalAdapter(list, activity as MainActivity)
            recyclerView.adapter = listAppAdapter
        }

    }

}