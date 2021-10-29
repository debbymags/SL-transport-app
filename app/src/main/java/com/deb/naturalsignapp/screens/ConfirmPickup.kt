package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import android.text.Html
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityConfirmPickupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfirmPickup : AppCompatActivity() {
    private lateinit var database : DatabaseReference

    private lateinit var binding: ActivityConfirmPickupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmPickupBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.yesBtn?.setOnClickListener {

            database = FirebaseDatabase.getInstance().getReference("Users")

            val uid = MyAppGlobal.Companion.uid
            val loc = MyAppGlobal.Companion.loc
            val des = MyAppGlobal.Companion.destination
            val ride = MyAppGlobal.Companion.ride
            val User = User(uid,loc,des,ride)
//            val UserD = User


            database.child(uid).setValue(User).addOnSuccessListener {
                Log.i("success", "db saved successfully")

            }.addOnFailureListener{
                Log.i("success", "db saved successfully")

            }



            RideComing.open(this)
        }

        val videoView =
            findViewById<View>(R.id.videoViewConfirm) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.confirm_order)
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
            context.startActivity(Intent(context, ConfirmPickup::class.java))
    }
}