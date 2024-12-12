package com.example.wether_app.workManagerDemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class RoomDbHelper : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDbHelper? = null

        fun getDatabase(context: Context): RoomDbHelper {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, RoomDbHelper::class.java, "userDB_WM_"
                ).build()
                INSTANCE = instance
                instance
            }

        }

    }

}