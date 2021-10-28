package com.deb.naturalsignapp.screens

import android.app.Application

class MyAppGlobal : Application() {
    companion object {
        var uid = ""
        var loc = ""
        var destination = ""
        var ride = ""
    }


}