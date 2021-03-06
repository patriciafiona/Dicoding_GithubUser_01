package com.path_studio.githubuser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.githubuser.Adapters.ListPopularRepoAdapter
import com.path_studio.githubuser.Models.RepoData
import com.path_studio.githubuser.Models.Repository
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.R
import com.path_studio.githubuser.Utils
import de.hdodenhof.circleimageview.CircleImageView

class UserDetailActivity : AppCompatActivity() {
    private lateinit var appBgImage: ImageView
    private lateinit var USERNAME: String
    private lateinit var AVATAR: String

    companion object {
        const val EXTRA_USER = "extra_user"
        const val MY_USERNAME = "patriciafiona"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //show all data into UI
        val data = intent.getParcelableExtra<User>(EXTRA_USER) as User
        showData(data)

        //Change App background Color
        settingAnimatedBackground()

        //show user repositories
        showRepository()

        //Hide or Show button fullow based on
        val btnFollow: Button = findViewById(R.id.btn_follow)
        if(data.username.equals(MY_USERNAME)){
            btnFollow.visibility = View.GONE
        }else{
            btnFollow.visibility = View.VISIBLE
        }
    }

    private fun showData(user: User){
        appBgImage = findViewById(R.id.detail_user_background_animated)
        val name: TextView = findViewById(R.id.detail_user_name)
        val username: TextView = findViewById(R.id.detail_user_username)
        val avatar: CircleImageView = findViewById(R.id.detail_user_avatar)
        val starred: TextView = findViewById(R.id.detail_user_starred)
        val location: TextView = findViewById(R.id.detail_user_location)
        val repositories: TextView = findViewById(R.id.detail_user_repositories)
        val company: TextView = findViewById(R.id.detail_user_company)
        val email: TextView = findViewById(R.id.detail_user_email)
        val link: TextView = findViewById(R.id.detail_user_link)
        val followers: TextView = findViewById(R.id.detail_user_followers)
        val followings: TextView = findViewById(R.id.detail_user_followings)

        name.text = user.name

        USERNAME = user.username.toString()
        username.text = USERNAME

        starred.text = user.starred.toString()
        location.text = user.location
        repositories.text = user.repository.toString()
        company.text = user.company
        email.text = user.email
        link.text = user.link
        followers.text = Utils.convertNumberFormat(user.followers)
        followings.text = Utils.convertNumberFormat(user.following)

        AVATAR = user.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        avatar.setImageResource(this.resources.getIdentifier(AVATAR, "drawable", this.packageName))

        Glide.with(this)
            .load(user.background)
            .apply(RequestOptions().override(800, 800))
            .into(appBgImage)
    }

    private fun settingAnimatedBackground(){
        val zoomInAnim: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)

        val animation = AnimationSet(false) //change to false
        animation.addAnimation(zoomInAnim)
        appBgImage.setAnimation(animation)
    }

    private fun showRepository(){
        val rvPopularRepo: RecyclerView = findViewById(R.id.rv_users_repo)
        rvPopularRepo.setHasFixedSize(true)

        val filterResult: ArrayList<Repository> = ArrayList(RepoData.getRepoList(this).filter { it.username == USERNAME })
        showRecyclerList(rvPopularRepo, filterResult)
    }

    private fun showRecyclerList(rvApp: RecyclerView, list: ArrayList<Repository>) {
        rvApp.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listAppAdapter = ListPopularRepoAdapter(list, this.applicationContext, AVATAR)
        rvApp.adapter = listAppAdapter
    }

}