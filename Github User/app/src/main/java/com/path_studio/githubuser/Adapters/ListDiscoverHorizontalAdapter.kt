package com.path_studio.githubuser.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.R
import de.hdodenhof.circleimageview.CircleImageView

class ListDiscoverHorizontalAdapter(val list: ArrayList<User>, val activity: MainActivity) : RecyclerView.Adapter<ListDiscoverHorizontalAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_col_discover_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = list[position]

        Glide.with(holder.itemView.context)
            .load(user.background)
            .apply(RequestOptions().override(500, 500))
            .into(holder.dispBackground)

        val avatarName:String = user.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        holder.dispAvatar.setImageResource(activity.resources.getIdentifier(avatarName, "drawable", activity.packageName))

        holder.dispUsername.text = user.username
        holder.dispName.text = user.name
        holder.dispStarred.text = user.starred.toString()
        holder.dispRepositories.text = user.repository.toString()

        holder.itemView.setOnClickListener {
            //
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dispBackground: ImageView = itemView.findViewById(R.id.discover_background)
        var dispAvatar: CircleImageView = itemView.findViewById(R.id.discover_avatar)
        var dispUsername: TextView = itemView.findViewById(R.id.discover_username)
        var dispName: TextView = itemView.findViewById(R.id.discover_name)
        var dispStarred: TextView = itemView.findViewById(R.id.discover_starred)
        var dispRepositories: TextView = itemView.findViewById(R.id.discover_repositories)
    }

}