package com.path_studio.githubuser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mancj.materialsearchbar.MaterialSearchBar
import com.path_studio.githubuser.R

class MainActivity : AppCompatActivity() {
    private lateinit var searchBar: MaterialSearchBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting the Bottom Navigator
        setBottomNav()
    }

    private fun setBottomNav(){
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_list_users,
            R.id.navigation_profile,
        )
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }

    override fun onResume() {
        super.onResume()
        clearSearchBar()
    }

    fun clearSearchBar(){
        searchBar = findViewById(R.id.searchBar)
        if(searchBar.isSearchOpened){
            searchBar.clearSuggestions()
            searchBar.closeSearch()
        }

    }

    fun setSearchBarVisibility(status: Int){
        val searchBar: MaterialSearchBar = this.findViewById(R.id.searchBar)
        when(status){
            0 -> {
                searchBar.visibility = View.GONE
            }
            1 -> {
                searchBar.visibility = View.VISIBLE
            }
        }
    }
}