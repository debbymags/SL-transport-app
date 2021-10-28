package com.deb.naturalsignapp.screens

import android.content.DialogInterface
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.fragment.app.FragmentManager
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.screens.greedyAlgo
import android.util.Log
import android.content.pm.PackageManager
import android.widget.*
import androidx.core.content.ContextCompat
import com.deb.naturalsignapp.BuildConfig
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random


class BottomSheet : BaseBottomSheet(
    R.layout.fragment_overflow_sheet,
    withHeight = false
) {



    private var rideSelected = ""
    private var onDismissed: (() -> Unit)? = null

    private var rideCLicked: (() -> Unit)? = null
    lateinit var locationText: TextView
    lateinit var rideBtn: Button
    lateinit var firstCar: View
    lateinit var secondCar: View
    lateinit var videoView: VideoView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView = view.findViewById(R.id.videoViewRide) as VideoView


        //Setting MediaController
        val mediaController = MediaController(this.context)
        mediaController.setAnchorView(videoView)
        //Setting video path in the URI
        val uri: Uri =
            Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.select_ride)
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()


        val list = listOf(800, 1600, 3000, 2500, 1700)
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]

        val list2 = listOf(700, 1500, 2000, 600, 400)
        val randomIndex2 = Random.nextInt(list2.size);
        val randomElement2 = list2[randomIndex2]

        val price = greedyAlgo()
        val imageList: MutableList<String> = price.generateImageList(randomElement2)

       var somn = getResources().getIdentifier(imageList[0] , "drawable", view.getContext().getPackageName())
        var somn2 = getResources().getIdentifier(imageList[1] , "drawable", view.getContext().getPackageName())


        val imageView1 = view.findViewById(R.id.imageView3) as ImageView
        imageView1.setImageResource(somn)

        val imageView2 = view.findViewById(R.id.fnote) as ImageView
        imageView2.setImageResource(somn2)


        val price2 = greedyAlgo()
        val imageList2: MutableList<String> = price2.generateImageList(randomElement)

        var somn3 = getResources().getIdentifier(imageList2[0] , "drawable", view.getContext().getPackageName())
        var somn4 = getResources().getIdentifier(imageList2[1] , "drawable", view.getContext().getPackageName())
        var somn5 = getResources().getIdentifier(imageList2[2] , "drawable", view.getContext().getPackageName())



        val imageView3 = view.findViewById(R.id.imageView4) as ImageView
        imageView3.setImageResource(somn3)

        val imageView4 = view.findViewById(R.id.f_two_note) as ImageView
        imageView4.setImageResource(somn4)

        val imageView5 = view.findViewById(R.id.f_one_note) as ImageView
        imageView5.setImageResource(somn5)


        locationText = view.findViewById(R.id.locationText)
        rideBtn = view.findViewById(R.id.select_ride_btn)
        firstCar = view.findViewById(R.id.first_car)
        secondCar = view.findViewById(R.id.second_car)

        rideBtn.setOnClickListener {
            MyAppGlobal.Companion.ride = rideSelected
            rideCLicked?.invoke()
            dismiss()
        }


        firstCar.setOnClickListener {
            val uri: Uri =
                Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.lite_ride)

            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

            secondCar.setBackground(null)
            firstCar.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.highlight))
            rideSelected = "Lite"

        }

        secondCar.setOnClickListener {
            val uri: Uri =
                Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.premium_ride)

            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

            firstCar.setBackground(null)
            secondCar.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.highlight))
            rideSelected = "Premium"
        }

        //locationText.text = "This is your selected location: $location"
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissed?.invoke()
    }

    companion object {
        fun open(fragmentManager: FragmentManager, selectRide : (() -> Unit), onClicked: (() -> Unit)? = null) =
            BottomSheet().apply {
                rideCLicked = selectRide
                onDismissed = onClicked
            }
                .show(fragmentManager, BottomSheet::class.java.simpleName)
    }
}