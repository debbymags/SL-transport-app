package com.deb.naturalsignapp.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivityEngConfirmRideBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EngConfirmRide : AppCompatActivity() {
    private lateinit var database : DatabaseReference


    private var binding: ActivityEngConfirmRideBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEngConfirmRideBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.confirmBtn?.setOnClickListener {

            database = FirebaseDatabase.getInstance().getReference("Users")

            var uid = MyAppGlobal.Companion.uid
            var loc = MyAppGlobal.Companion.loc
            var des = MyAppGlobal.Companion.destination
            var ride = MyAppGlobal.Companion.ride
            val User = User(uid,loc,des,ride)


            database.child(uid).setValue(User).addOnSuccessListener {
                Log.i("success", "db saved successfully")
            }.addOnFailureListener{
                Log.i("success", "db saved successfully")

            }

            EngRideComing.open(this)
        }
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
            context.startActivity(Intent(context, EngConfirmRide::class.java))
    }
}