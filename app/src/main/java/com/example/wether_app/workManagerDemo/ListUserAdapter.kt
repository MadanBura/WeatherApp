package com.example.wether_app.workManagerDemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wether_app.R
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView


class ListUserAdapter(private val userList: List<User>,
   val funToDel : onItemClick
    ) : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {

    interface onItemClick{
       fun onClickToDelete(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customuser, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.fullLayout.setOnClickListener {
            funToDel.onClickToDelete(user)
        }

        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val fullLayout = itemView.findViewById<LinearLayout>(R.id.touch)
        val firstName = itemView.findViewById<TextView>(R.id.first_name)
        val lastName = itemView.findViewById<TextView>(R.id.last_name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val phoneNo = itemView.findViewById<TextView>(R.id.phone_no)
        val gender = itemView.findViewById<TextView>(R.id.gender)
        val syncedStatus = itemView.findViewById<TextView>(R.id.synced_status)
        val timeStamp = itemView.findViewById<TextView>(R.id.timeStamp)


        fun bind(user: User) {
            firstName.text = user.first_name
            lastName.text = user.last_name
            email.text = user.email
            phoneNo.text = user.phone_no.toString()
            gender.text = user.gender
            syncedStatus.text = user.isSync.toString()
            timeStamp.text = user.timeStamp
        }
    }
}
