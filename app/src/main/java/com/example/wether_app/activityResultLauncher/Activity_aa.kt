package com.example.wether_app.activityResultLauncher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wether_app.R
import com.example.wether_app.databinding.ActivityAaBinding

class Activity_aa : AppCompatActivity() {

    private lateinit var binding: ActivityAaBinding

    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("result_key")
                binding.resultTextView.text = data ?: "No data received"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchButton.setOnClickListener {
            val intent = Intent(this, Activity_bb::class.java)
            resultLauncher.launch(intent)
        }

    }
}