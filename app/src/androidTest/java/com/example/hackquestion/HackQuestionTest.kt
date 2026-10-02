package com.example.hackquestion

import org.junit.Assert.assertEquals
import org.junit.Test

class HackQuestionTest {

    @Test
    fun scoreCalculationWorks() {
        val score = 8
        val total = 10

        assertEquals(80, (score * 100) / total)
    }
}
