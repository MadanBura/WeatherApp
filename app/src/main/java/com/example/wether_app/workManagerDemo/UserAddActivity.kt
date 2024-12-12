package com.example.wether_app.workManagerDemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wether_app.databinding.ActivityUserAddBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAddBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepo(RoomDbHelper.getDatabase(this).userDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPass = binding.confirmPasswordEditText.text.toString()
            val gender = binding.genderEditText.text.toString()
            val phoneNo = binding.phoneNoEditText.text.toString()

            if (password != confirmPass) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                password.isEmpty() || gender.isEmpty() || phoneNo.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val phoneLong = phoneNo.toLongOrNull()
            if (phoneLong == null) {
                Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(
                first_name = firstName,
                last_name = lastName,
                email = email,
                password = password,
                confirm_password = confirmPass,
                gender = gender,
                phone_no = phoneLong
            )

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val result = userViewModel.addUserIntoDb(user)

                    if (result) {
                        runOnUiThread {
                            Toast.makeText(this@UserAddActivity, "User registered successfully", Toast.LENGTH_SHORT).show()

                            binding.firstNameEditText.text.clear()
                            binding.lastNameEditText.text.clear()
                            binding.emailEditText.text.clear()
                            binding.passwordEditText.text.clear()
                            binding.confirmPasswordEditText.text.clear()
                            binding.genderEditText.text.clear()
                            binding.phoneNoEditText.text.clear()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@UserAddActivity, "Error adding user", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@UserAddActivity, "Error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        binding.floatingButton.setOnClickListener {
            Intent(this, ListAllRegisteredUsers::class.java).also {
                startActivity(it)
            }
        }


    }
}
