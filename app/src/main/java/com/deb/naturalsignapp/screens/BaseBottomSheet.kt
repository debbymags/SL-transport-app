package com.deb.naturalsignapp.screens

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.deb.naturalsignapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheet(
    @LayoutRes val containerId: Int,
    private val withHeight: Boolean = true
) :
    BottomSheetDialogFragment(){
    private var dialogView: View? = null

    private var bottomSheetBehaviorState: Int? = BottomSheetBehavior.STATE_EXPANDED


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_App_BottomSheetDialog)
    }

    fun setBottomSheetBehaviorState(state: Int) {
        this.bottomSheetBehaviorState = state
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view?.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(containerId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val dialog = dialog as BottomSheetDialog
                val bottomSheet =
                    dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.state = bottomSheetBehaviorState ?: BottomSheetBehavior.STATE_EXPANDED

                // Only calculate full height when withHeight is ture
                // else use containerId layout height
                if (withHeight) {
                    val newHeight = activity?.window?.decorView?.measuredHeight
                    val viewGroupLayoutParams = bottomSheet.layoutParams
                    viewGroupLayoutParams.height = newHeight ?: 0
                    bottomSheet.layoutParams = viewGroupLayoutParams
                }
            }
        })
        dialogView = view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroyView() {
        dialogView?.viewTreeObserver?.addOnGlobalLayoutListener(null)
        super.onDestroyView()
    }
}