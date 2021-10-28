package com.deb.naturalsignapp.screens

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySelectLanguageBinding
import android.net.Uri
import android.net.Uri.parse
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.content.ContextCompat
import java.util.*


class SelectLanguageActivity : AppCompatActivity() {
    private var binding: ActivitySelectLanguageBinding? = null
    lateinit var signBtn: Button
    lateinit var engBtn: Button
    lateinit var helpBtn: ClipData.Item

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

        val videoView =
            findViewById<View>(R.id.videoView) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            parse("android.resource://" + getPackageName() + "/" + R.raw.welcome_video)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        signBtn = findViewById(R.id.sign_lang_btn)
        engBtn = findViewById(R.id.english_btn)


        Timer().schedule(object : TimerTask() {
            override fun run() {
                signBtn.setBackgroundColor(resources.getColor(R.color.highlight))
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        signBtn.setBackgroundColor(resources.getColor(R.color.black))
                    }
                }, 4000)
            }
        }, 13000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                engBtn.setBackgroundColor(resources.getColor(R.color.highlight))
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        engBtn.setBackgroundColor(resources.getColor(R.color.black))
                    }
                }, 4000)
            }
        }, 22000)






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
            context.startActivity(Intent(context, SelectLanguageActivity::class.java))
    }
}