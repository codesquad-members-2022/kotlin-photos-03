package com.example.photos

class ColorFactory {

    companion object {
        fun makeRandomRGB(): BackGroundColor {
            val red = (0..255).random()
            val blue = (0..255).random()
            val green = (0..255).random()
            return BackGroundColor(red, green, blue)
        }
    }
}