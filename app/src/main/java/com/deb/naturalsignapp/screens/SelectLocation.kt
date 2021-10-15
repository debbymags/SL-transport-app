package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySearchLoactionBinding
import com.deb.naturalsignapp.databinding.ActivitySelectLocationBinding

class SelectLocation : AppCompatActivity() {
    private var binding: ActivitySelectLocationBinding? = null

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        binding?.yesBtn?.setOnClickListener {
            SearchLoaction.open(this)

        }
    }


    
    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, SelectLocation::class.java))
    }
}