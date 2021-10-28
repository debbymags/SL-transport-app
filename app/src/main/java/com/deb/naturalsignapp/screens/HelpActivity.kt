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
import com.deb.naturalsignapp.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    private var binding: ActivityHelpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val videoView =
            findViewById<View>(R.id.videoViewHelp) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.about_app)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.help_menu, menu)
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
            else ->
                return super.onContextItemSelected(item)
        }


    }


    companion  object {
        fun open(context: Context) =
            context.startActivity(Intent(context, HelpActivity::class.java))
    }
}