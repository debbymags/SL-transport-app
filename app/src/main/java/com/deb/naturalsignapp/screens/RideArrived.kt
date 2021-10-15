package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityRideArrivedBinding

class RideArrived : AppCompatActivity() {
    private var binding: ActivityRideArrivedBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRideArrivedBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Toast.makeText(this, "your ride is on it's way", Toast.LENGTH_LONG).show()
        binding?.okBtn?.setOnClickListener {
            Toast.makeText(this, "your driver has arrived", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, RideArrived::class.java))
    }
}