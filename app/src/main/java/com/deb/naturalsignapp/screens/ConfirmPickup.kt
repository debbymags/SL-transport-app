package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityConfirmPickupBinding

class ConfirmPickup : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmPickupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmPickupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.yesBtn?.setOnClickListener {
            RideArrived.open(this)
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, ConfirmPickup::class.java))
    }
}