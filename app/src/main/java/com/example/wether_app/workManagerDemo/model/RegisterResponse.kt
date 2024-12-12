package com.example.wether_app.workManagerDemo.model

data class RegisterResponse(
    val data: Data?,
    val message: String,
    val status: Int,
    val user_msg: String
)