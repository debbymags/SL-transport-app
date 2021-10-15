package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.continueBtn?.setOnClickListener {
            SelectLanguageActivity.open(this)
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, MainActivity::class.java))
    }
}
