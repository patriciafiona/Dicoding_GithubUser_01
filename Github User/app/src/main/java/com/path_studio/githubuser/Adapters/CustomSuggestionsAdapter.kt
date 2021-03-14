package com.path_studio.githubuser.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Activities.UserDetailActivity
import com.path_studio.githubuser.Models.User
import com.path_studio.githubuser.R

class CustomSuggestionsAdapter(inflater: LayoutInflater?, val activity: MainActivity) : SuggestionsAdapter<User, CustomSuggestionsAdapter.SuggestionHolder>(inflater)
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomSuggestionsAdapter.SuggestionHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_suggestion, parent, false)
        return SuggestionHolder(view)
    }

    override fun onBindSuggestionHolder(
        suggestion: User,
        holder: CustomSuggestionsAdapter.SuggestionHolder,
        position: Int
    ) {

        holder.name.text = suggestion.name

        val avatarName:String = suggestion.avatar.toString().replace("res/drawable/", "").replace(".png","") //exp before replace: res/drawable/user7.png
        holder.avatar.setImageResource(activity.resources.getIdentifier(avatarName, "drawable", activity.packageName))

        holder.itemView.setOnClickListener {
            //show detail page
            val i = Intent(activity, UserDetailActivity::class.java)
            i.putExtra(UserDetailActivity.EXTRA_USER, suggestion)
            activity.startActivity(i)
        }
    }

    override fun getSingleViewHeight(): Int {
        return 60
    }

    inner class SuggestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.suggestion_appName)
        var avatar: ImageView = itemView.findViewById(R.id.suggestion_appLogo)
    }
}