package com.path_studio.githubuser.Models

data class Repository (
    var username: String = "",
    var repo_name: String = "",
    var repo_detail: String = "",
    var repo_category: String = "",
    var favorite: Int = 0
)