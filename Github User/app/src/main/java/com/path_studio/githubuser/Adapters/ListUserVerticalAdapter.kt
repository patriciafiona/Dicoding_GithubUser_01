package com.path_studio.githubuser.Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.R

class ListUserVerticalAdapter (val list: ArrayList<User>, val activity: MainActivity) : RecyclerView.Adapter<ListUserVerticalAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val users = list[position]

        val avatarName:String = users.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        holder.disp_item_avatar.setImageResource(activity.resources.getIdentifier(avatarName, "drawable", activity.packageName))

        holder.disp_item_name.text = users.name
        holder.disp_item_username.text = users.username

        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, "You Choose: " + listApp.get(holder.adapterPosition).appName, Toast.LENGTH_SHORT).show()
            //show detail page
            /*val i = Intent(activity, DetailApp::class.java)
            i.putExtra("appID", list[holder.adapterPosition].appID)
            activity.startActivity(i)*/
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var disp_item_avatar: ImageView = itemView.findViewById(R.id.item_row_avatar)
        var disp_item_name: TextView = itemView.findViewById(R.id.item_row_name)
        var disp_item_username: TextView = itemView.findViewById(R.id.item_row_username)
    }

}