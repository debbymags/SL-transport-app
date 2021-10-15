package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityEngRideComingBinding

class EngRideComing : AppCompatActivity() {
    private var binding: ActivityEngRideComingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEngRideComingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.confirmBtn?.setOnClickListener {
            EnfDriverComing.open(this)
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, EngRideComing::class.java))
    }
}