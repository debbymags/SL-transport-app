package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityRideArrivedBinding
import com.deb.naturalsignapp.databinding.ActivityRideComingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RideComing : AppCompatActivity() {


    private var binding: ActivityRideComingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRideComingBinding.inflate(layoutInflater)
        setContentView(binding?.root)


//        Toast.makeText(this, "your ride is on it's way", Toast.LENGTH_LONG).show()

        binding?.okBtn?.setOnClickListener {
//            Toast.makeText(this, "your driver has arrived", Toast.LENGTH_LONG).show()
            RideArrived.open(this)
        }

        val videoView =
            findViewById<View>(R.id.videoViewComing) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.ride_coming)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

    }

    companion object {
        fun open(context: Context) =
            context.startActivity(Intent(context, RideComing::class.java))
    }
}