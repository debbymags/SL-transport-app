package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R

class EnfDriverComing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enf_driver_coming)
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, EnfDriverComing::class.java))
    }
}