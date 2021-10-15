package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityEngConfirmRideBinding

class EngConfirmRide : AppCompatActivity() {
    private var binding: ActivityEngConfirmRideBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEngConfirmRideBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.confirmBtn?.setOnClickListener {
            EngRideComing.open(this)
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, EngConfirmRide::class.java))
    }
}