package com.path_studio.githubuser.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Models.Repository
import com.path_studio.githubuser.R
import de.hdodenhof.circleimageview.CircleImageView

class ListPopularRepoAdapter (val list: ArrayList<Repository>, val activity: MainActivity, val userAvatar: String) : RecyclerView.Adapter<ListPopularRepoAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_col_popular_repository, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val repo = list[position]

        holder.dispRepoUsername.text = repo.username
        holder.dispRepoName.text = repo.repo_name
        holder.dispRepoDetail.text = repo.repo_detail
        holder.dispRepoCategoryTxt.text = repo.repo_category

        var temp_fav = ""
        if(repo.favorite >= 1000){
            // Kilo
            temp_fav = repo.favorite.toString() + "K"
        }else if(repo.favorite >= 1000000){
            //Million
            temp_fav = repo.favorite.toString() + "M"
        }else{
            temp_fav = repo.favorite.toString()
        }
        holder.dispFav.text = temp_fav

        when(repo.repo_category){
            "Java" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.red)
            "PHP" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.purple)
            "JavaScript" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.amber)
            "C" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.blue_500)
            "C++" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.blue_900)
            "Dart" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.purple_900)
            "Kotlin" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.cyan)
            "Shell" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.yellow)
            "HTML" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.amber_200)
            "CSS" -> holder.dispRepoCategoryIndicator.setImageResource(R.color.blue_grey_600)
            else -> holder.dispRepoCategoryIndicator.setImageResource(R.color.grey_600)
        }

        holder.dispRepoAvatar.setImageResource(activity.resources.getIdentifier(userAvatar, "drawable", activity.packageName))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dispRepoUsername: TextView = itemView.findViewById(R.id.popular_repo_username)
        var dispRepoName: TextView = itemView.findViewById(R.id.popular_repo_name)
        var dispRepoDetail: TextView = itemView.findViewById(R.id.popular_repo_detail)
        var dispRepoCategoryTxt: TextView = itemView.findViewById(R.id.popular_repo_category_name)
        var dispRepoCategoryIndicator: CircleImageView = itemView.findViewById(R.id.popular_repo_category)
        var dispFav: TextView = itemView.findViewById(R.id.popular_repo_favorite)
        var dispRepoAvatar: CircleImageView = itemView.findViewById(R.id.popular_repo_avatar)
    }

}