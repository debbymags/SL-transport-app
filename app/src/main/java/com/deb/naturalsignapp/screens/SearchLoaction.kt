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
import com.google.firebase.database.DatabaseReference
import com.deb.naturalsignapp.databinding.ActivitySearchLoactionBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase

class SearchLoaction : AppCompatActivity() {
//    private val databaseReference = FirebaseDatabase.getInstance().reference

    private var overflowDisplay: Boolean = false
    private var binding: ActivitySearchLoactionBinding? = null
    var location = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchLoactionBinding.inflate(layoutInflater)
        setContentView(binding?.root)



//        location = binding?.locationEdt?.text.toString()
        binding?.searchBtn?.setOnClickListener {
            if (binding?.locationEdt?.text.toString().isEmpty()){
                Toast.makeText(this, "location can not be empty", Toast.LENGTH_SHORT).show()
            } else {


                MyAppGlobal.Companion.loc = binding?.locationEdt?.text.toString()

                InputDestination.open(this)
            }

        }

        val videoView =
            findViewById<View>(R.id.videoViewLocation) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.input_location)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

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
            context.startActivity(Intent(context, SearchLoaction::class.java))
    }
}