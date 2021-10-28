package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityInputDestinationBinding
import com.deb.naturalsignapp.databinding.ActivitySearchLoactionBinding

class InputDestination : AppCompatActivity() {
    private var overflowDisplay: Boolean = false
    private var binding: ActivityInputDestinationBinding? = null
    var destination = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDestinationBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        destination = binding?.destinationEdt?.text.toString()

        binding?.searchBtn?.setOnClickListener {
            if (binding?.destinationEdt?.text.toString().isEmpty()){
                Toast.makeText(this, "destination can not be empty", Toast.LENGTH_SHORT).show()
            } else {
                MyAppGlobal.Companion.destination = binding?.destinationEdt?.text.toString()
                manageOverFlowIconClick()
            }

        }

        val videoView =
            findViewById<View>(R.id.videoViewDestination) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.input_destination)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

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
//                ConfirmPickup.open(this)
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
                ConfirmPickup.open(this)
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
            context.startActivity(Intent(context, InputDestination::class.java))

    }
}