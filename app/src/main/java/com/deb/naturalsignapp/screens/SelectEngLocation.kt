package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySelectEngLocationBinding

class SelectEngLocation : AppCompatActivity() {
    private var overflowDisplay: Boolean = false

    private var binding: ActivitySelectEngLocationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectEngLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.autoBtn?.setOnClickListener {
            Toast.makeText(this, "your location has been set automatically", Toast.LENGTH_SHORT).show()
            manageOverFlowIconClick()
        }
        binding?.manualBtn?.setOnClickListener {
            ActivityEngSearchLoaction.open(this)
        }
    }

    private fun manageOverFlowIconClick() {
        if (overflowDisplay) {
            val overflowBottomSheet = supportFragmentManager
                .findFragmentByTag(BottomSheet::class.java.simpleName)
            overflowBottomSheet?.let {
                if (it.isVisible) {
                    val dialog = overflowBottomSheet
                            as? BaseBottomSheet
                    dialog?.dismiss()
                }
            }
        } else {
            BottomSheet.open(supportFragmentManager, {
                EngConfirmRide.open(this)
            }) {
                overflowDisplay = false
            }
        }
        overflowDisplay = !overflowDisplay
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, SelectEngLocation::class.java))
    }
}