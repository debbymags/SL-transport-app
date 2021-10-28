package com.deb.naturalsignapp.screens

import android.content.DialogInterface
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.deb.naturalsignapp.R
import com.deb.naturalsignapp.screens.greedyAlgo
import android.util.Log
import kotlin.random.Random


class SecondBottomSheet : BaseBottomSheet(
    R.layout.location_destination_conirm,
    false
) {


    private var location = ""
    private var destination = ""
    private var onDismissed: (() -> Unit)? = null

    private var rideCLicked: (() -> Unit)? = null
    lateinit var locationText: TextView
    lateinit var okBtn: Button
    lateinit var locV: TextView
    lateinit var desV: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




//        locationText = view.findViewById(R.id.locationText)
        okBtn = view.findViewById(R.id.select_ok_btn)
        locV = view.findViewById(R.id.locText)
        desV = view.findViewById(R.id.desText)

//        locV.setText(MyAppGlobal().smn)
        locV.setText(MyAppGlobal.Companion.loc)
        desV.setText(MyAppGlobal.Companion.destination)

        okBtn.setOnClickListener {
            rideCLicked?.invoke()
            dismiss()
        }
        //locationText.text = "This is your selected location: $location"
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissed?.invoke()
    }

    companion object {
        fun open(fragmentManager: FragmentManager, selectRide : (() -> Unit), onClicked: (() -> Unit)? = null) =
            SecondBottomSheet().apply {
                rideCLicked = selectRide
                onDismissed = onClicked
            }
                .show(fragmentManager, SecondBottomSheet::class.java.simpleName)
    }
}