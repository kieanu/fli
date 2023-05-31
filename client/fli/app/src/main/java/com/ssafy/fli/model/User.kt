package com.ssafy.fli.model

data class User(val name: String, val userId: String, val userSns: Int, val token: String) {
    constructor(name: String, userId: String, userSns: Int) : this(name, userId, userSns, "")
}