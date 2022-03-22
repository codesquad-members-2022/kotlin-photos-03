package com.codesquad.pair_photo_album

import com.example.photos.BackGroundColor
import com.example.photos.ColorFactory
import org.junit.Test
import org.junit.Assert.*


class RandomColorTest {

    private val colorSize= 40

    @Test
    fun testColorEqual(){
        var firstColor= BackGroundColor(255,200,10)
        var secondColor= BackGroundColor(255,200,10)
        assertEquals(true, firstColor==secondColor)
        firstColor= BackGroundColor(255,200,5)
        assertEquals(false, firstColor==secondColor)
    }

    @Test
    fun testRandomColorList() {
        val randomColorList = List<BackGroundColor>(colorSize) { ColorFactory.makeRandomRGB() }
        val randomColorSet= randomColorList.toSet()
        assertEquals(true,  randomColorList.size==colorSize)
        assertEquals(true, (randomColorList.size==randomColorSet.size))
    }



}