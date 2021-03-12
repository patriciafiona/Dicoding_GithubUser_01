package com.path_studio.githubuser.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Adapters.ListUserVerticalAdapter
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.Models.UserData
import com.path_studio.githubuser.R

class ListUserFragment : Fragment() {

    private lateinit var rvUser: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_list_user, container, false)

        //show list of apps
        rvUser = rootView.findViewById(R.id.rv_users)
        rvUser.setHasFixedSize(true)

        showRecyclerList()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Show Search Bar
        (activity as MainActivity).setSearchBarVisibility(1)
        (activity as MainActivity).clearSearchBar()
    }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(activity)
        val listAppAdapter = ListUserVerticalAdapter(UserData.getListUsers(resources), activity as MainActivity)
        rvUser.adapter = listAppAdapter
    }

}