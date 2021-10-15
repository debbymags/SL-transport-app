package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySelectLanguageBinding

class SelectLanguageActivity : AppCompatActivity() {
    private var binding: ActivitySelectLanguageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.signLangBtn?.setOnClickListener {
            SelectLocation.open(this)
        }

        binding?.englishBtn?.setOnClickListener {
            SelectEngLocation.open(this)
        }
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, SelectLanguageActivity::class.java))
    }
}