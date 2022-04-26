package com.example.pagginatedlazycolumncompose

data class UserResponse (
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<User>,
    var support: Support
)

data class User(
    var id: Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String
)

data class Support(
    var url: String,
    var text: String
)