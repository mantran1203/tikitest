package com.man.myapplication

import com.man.myapplication.utils.StringUtil
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class KeyWordTest{

    val sampleKeywords = mutableListOf(
        "xiaomi",
        "bitis hunter",
        "bts",
        "balo",
        "bitis hunter x",
        "tai nghe",
        "harry potter",
        "anker",
        "iphone",
        "balo nữ",
        "nguyễn nhật ánh",
        "đắc nhân tâm",
        "ipad",
        "senka",
        "tai nghe bluetooth",
        "son",
        "maybelline",
        "laneige",
        "kem chống nắng",
        "anh chính là thanh xuân của em")

    val expectedSampleKeywords = mutableListOf(
        "xiaomi",
        "bitis\nhunter",
        "bts",
        "balo",
        "bitis\nhunter x",
        "tai\nnghe",
        "harry\npotter",
        "anker",
        "iphone",
        "balo\nnữ",
        "nguyễn\nnhật ánh",
        "đắc nhân\ntâm",
        "ipad",
        "senka",
        "tai nghe\nbluetooth",
        "son",
        "maybelline",
        "laneige",
        "kem chống\nnắng",
        "anh chính là\nthanh xuân của em")

    @Test
    fun testHandleKeyword() {
        sampleKeywords.mapIndexed { index, s -> assertEquals(StringUtil.handleKeywords(s), expectedSampleKeywords[index]) }
    }
}