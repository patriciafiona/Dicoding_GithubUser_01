package com.path_studio.githubuser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.path_studio.githubuser.Adapters.CustomSuggestionsAdapter
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.Models.UserData
import com.path_studio.githubuser.R
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var searchBar: MaterialSearchBar
    private var list: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting the Bottom Navigator
        setBottomNav()

        //set Material Search Bar
        settingSearch()
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

    private fun settingSearch() {
        searchBar = findViewById(R.id.searchBar)
        searchBar.setMaxSuggestionCount(5)

        //enable search bar callbacks
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customSuggestionsAdapter = CustomSuggestionsAdapter(inflater, this@MainActivity)

        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {}

            override fun onSearchConfirmed(text: CharSequence) {
                //startSearch(text.toString(), true, null, true);
            }

            override fun onButtonClicked(buttonCode: Int) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    //opening or closing a navigation drawer
                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    searchBar.clearSuggestions()
                    searchBar.closeSearch()
                }
            }
        })
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter)

                if (searchBar.text.isNotEmpty()) {
                    if (searchBar.text.length > 1) {
                        //get data that contain searchBar.text
                        list = UserData.getDataFromName(resources, searchBar.text)
                        customSuggestionsAdapter.suggestions = list
                        searchBar.showSuggestionsList()
                    }
                } else {
                    searchBar.clearSuggestions()
                    searchBar.hideSuggestionsList()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        searchBar.setSuggestionsClickListener(object :
            SuggestionsAdapter.OnItemViewClickListener {
            override fun OnItemClickListener(position: Int, v: View) {}
            override fun OnItemDeleteListener(position: Int, v: View) {}
        })

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