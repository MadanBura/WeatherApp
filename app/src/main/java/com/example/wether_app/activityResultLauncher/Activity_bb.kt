package com.example.wether_app.activityResultLauncher

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wether_app.R
import com.example.wether_app.databinding.ActivityAaBinding
import com.example.wether_app.databinding.ActivityBbBinding

class Activity_bb : AppCompatActivity() {

    private lateinit var binding: ActivityBbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {

            val result = binding.inputEditText.text.toString()
            val resultIntent = Intent().apply {
                putExtra("result_key", result)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }
}