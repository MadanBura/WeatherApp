package com.example.wether_app.workManagerDemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.wether_app.R
import com.example.wether_app.databinding.ActivityListAllRegisteredUsersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import android.content.Context

class ListAllRegisteredUsers : AppCompatActivity(), ListUserAdapter.onItemClick{

    private lateinit var binding: ActivityListAllRegisteredUsersBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepo(RoomDbHelper.getDatabase(this).userDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAllRegisteredUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.getAllUsers.observe(this, Observer { userList ->
            val adapter = ListUserAdapter(userList, this)
            binding.recyclerView.adapter = adapter
        })

        binding.syncAll.setOnClickListener {

//            val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresCharging(true)
//                .build()
//
//            val workRequest = OneTimeWorkRequestBuilder<UserSyncWorker>()
//                .setConstraints(constraints)
//                .build()
//            WorkManager.getInstance(this).enqueue(workRequest)

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // Only run when there is an internet connection
                .build()

            // Create a PeriodicWorkRequest
            val periodicWorkRequest = PeriodicWorkRequest.Builder(
                UserSyncWorker::class.java,
                15, // Interval
                TimeUnit.MINUTES
            )
                .setConstraints(constraints) // Apply constraints


               //BackOffPolicies
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL, // Choose between LINEAR or EXPONENTIAL
                    1, // Initial backoff delay
                    TimeUnit.MINUTES
                )
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                "UserSyncWork",
                androidx.work.ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
            Toast.makeText(this, "Sync started", Toast.LENGTH_SHORT).show()


        }

    }

    override fun onClickToDelete(user: User) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete User")
            .setMessage("Are you sure you want to delete this user?")
            .setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    userViewModel.deleteFromDb(user.id)
                }
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}
