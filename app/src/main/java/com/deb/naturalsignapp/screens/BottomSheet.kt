package com.deb.naturalsignapp.screens

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.deb.naturalsignapp.R



class BottomSheet : BaseBottomSheet(
    R.layout.fragment_overflow_sheet,
    false
) {


    private var location = ""
    private var onDismissed: (() -> Unit)? = null

    private var rideCLicked: (() -> Unit)? = null
    lateinit var locationText: TextView
    lateinit var rideBtn: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationText = view.findViewById(R.id.locationText)
        rideBtn = view.findViewById(R.id.select_ride_btn)
        rideBtn.setOnClickListener {
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
            BottomSheet().apply {
                rideCLicked = selectRide
                onDismissed = onClicked
            }
                .show(fragmentManager, BottomSheet::class.java.simpleName)
    }
}