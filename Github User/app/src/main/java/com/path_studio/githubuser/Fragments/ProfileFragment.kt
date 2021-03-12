package com.path_studio.githubuser.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.Models.UserData
import com.path_studio.githubuser.R
import org.w3c.dom.Text

class ProfileFragment : Fragment() {

    protected val MY_POSITION: Int = 10
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

        //show data from arraylist
        listUsers = UserData.getListUsers(resources)
        val my_data = listUsers[MY_POSITION]

        myName.text = my_data.name
        myUsername.text = my_data.username
        myCompany.text = my_data.company
        myLocation.text = my_data.location
        myLink.text = my_data.link
        myFollowers.text = my_data.followers.toString()
        myFollowings.text = my_data.following.toString()

        val avatarName:String = my_data.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        myAvatar.setImageResource((activity as MainActivity).resources.getIdentifier(avatarName, "drawable", (activity as MainActivity).packageName))

    }

}