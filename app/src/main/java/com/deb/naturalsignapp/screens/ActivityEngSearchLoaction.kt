package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityEngSearchLoactionBinding

class ActivityEngSearchLoaction : AppCompatActivity() {
    private var binding: ActivityEngSearchLoactionBinding? = null
    private var overflowDisplay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEngSearchLoactionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.engSearchBtn?.setOnClickListener {
            if (binding?.engLocationEdt?.text.toString().isEmpty()){
                Toast.makeText(this, "location can not be empty", Toast.LENGTH_SHORT).show()
            } else {
                manageOverFlowIconClick()
            }
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
            context.startActivity(Intent(context, ActivityEngSearchLoaction::class.java))
    }
}