package com.example.photos

import android.graphics.Color

data class BackGroundColor (val red: Int, val green: Int, val blue: Int) {
    fun getColor(): Int {
        return Color.rgb(red, green, blue)
    }

}