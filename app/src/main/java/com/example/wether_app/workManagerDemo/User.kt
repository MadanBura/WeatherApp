package com.example.wether_app.workManagerDemo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userData_WM")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val confirm_password: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val password: String,
    val phone_no: Long,
    val isSync : Boolean = false,
    val timeStamp : String = ""
)