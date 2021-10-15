package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySearchLoactionBinding

class SearchLoaction : AppCompatActivity() {
    private var overflowDisplay: Boolean = false
    private var binding: ActivitySearchLoactionBinding? = null
    var location = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchLoactionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        location = binding?.locationEdt?.text.toString()
        binding?.searchBtn?.setOnClickListener {
            if (binding?.locationEdt?.text.toString().isEmpty()){
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
                ConfirmPickup.open(this)
            }) {
                overflowDisplay = false
            }
        }
        overflowDisplay = !overflowDisplay
    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, SearchLoaction::class.java))
    }
}