package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityEngSearchLoactionBinding
import com.deb.naturalsignapp.databinding.ActivityInputEngDestinationBinding

class InputEngDestination : AppCompatActivity() {
    private var binding: ActivityInputEngDestinationBinding? = null
    private var overflowDisplay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEngDestinationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.engDestSearchBtn?.setOnClickListener {
            if (binding?.engDestinationEdt?.text.toString().isEmpty()){
                Toast.makeText(this, "destination can not be empty", Toast.LENGTH_SHORT).show()
            } else {
                MyAppGlobal.Companion.destination = binding?.engDestinationEdt?.text.toString()
                manageOverFlowIconClick()
            }
        }
    }

    private fun manageOverFlowIconClick() {
        if (overflowDisplay) {
            val overflowBottomSheet = supportFragmentManager
                .findFragmentByTag(SecondBottomSheet::class.java.simpleName)
            overflowBottomSheet?.let {
                if (it.isVisible) {
                    val dialog = overflowBottomSheet
                            as? BaseBottomSheet
                    dialog?.dismiss()
                }
            }
        } else {
            SecondBottomSheet.open(supportFragmentManager, {
//                EngConfirmRide.open(this)
                overflowDisplay = false
                manageAnotherOverFlowIconClick()
            }) {
                overflowDisplay = false
            }
        }
        overflowDisplay = !overflowDisplay
    }

    private fun manageAnotherOverFlowIconClick() {
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

    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_btn -> {
//                finish()
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(homeIntent)
                return true
            }
            R.id.back_btn -> {
                finish()
                return true
            }
            R.id.help_btn -> {
                val helpIntent = Intent(this, HelpActivity::class.java)
                startActivity(helpIntent)
                return true
            }
            else ->
                return super.onContextItemSelected(item)
        }


    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, InputEngDestination::class.java))
    }
}