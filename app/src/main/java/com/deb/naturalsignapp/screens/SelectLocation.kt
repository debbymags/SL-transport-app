package com.deb.naturalsignapp.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.databinding.ActivitySearchLoactionBinding
import com.deb.naturalsignapp.databinding.ActivitySelectLocationBinding
import com.google.android.gms.location.*
import java.util.*

class SelectLocation : AppCompatActivity() {
    lateinit var oneBtn: CardView
    lateinit var twoBtn: CardView

    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    private var binding: ActivitySelectLocationBinding? = null

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.twoBtn?.setOnClickListener {
            SearchLoaction.open(this)

        }
        binding?.oneBtn?.setOnClickListener {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            getLastLocation()

        }

        val videoView =
            findViewById<View>(R.id.videoViewBook) as VideoView //casting to VideoView is not Strictly required above API level 26


        //Setting MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.book_ride)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()


        oneBtn = findViewById(R.id.one_btn)
        twoBtn = findViewById(R.id.two_btn)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                oneBtn.setBackgroundColor(resources.getColor(R.color.highlight))
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        oneBtn.setBackgroundColor(resources.getColor(R.color.black))
                    }
                }, 4000)
            }
        }, 13000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                twoBtn.setBackgroundColor(resources.getColor(R.color.highlight))
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        twoBtn.setBackgroundColor(resources.getColor(R.color.black))
                    }
                }, 4000)
            }
        }, 22000)

    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
//                        findViewById<TextView>(R.id.lat2TextView).text = location.latitude.toString()
//                        findViewById<TextView>(R.id.lon2TextView).text = location.longitude.toString()
                        var locat = location.latitude.toString()+","+location.longitude.toString()
                        MyAppGlobal.Companion.loc = locat
                        Toast.makeText(this, "your location has been set automatically", Toast.LENGTH_SHORT).show()
                        InputDestination.open(this)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
        Toast.makeText(this, "your location has been set automatically", Toast.LENGTH_SHORT).show()
        InputDestination.open(this)
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
//            findViewById<TextView>(R.id.lat2TextView).text = mLastLocation.latitude.toString()
//            findViewById<TextView>(R.id.lon2TextView).text = mLastLocation.longitude.toString()
            var locat = mLastLocation.latitude.toString()+","+mLastLocation.longitude.toString()
            MyAppGlobal.Companion.loc = locat
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
            context.startActivity(Intent(context, SelectLocation::class.java))
    }
}