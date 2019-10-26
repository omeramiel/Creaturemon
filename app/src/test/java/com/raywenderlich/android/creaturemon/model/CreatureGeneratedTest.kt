package com.raywenderlich.android.creaturemon.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreatureGeneratedTest {
    private lateinit var creatureGenerator: CreatureGenerator

    @Before
    fun setup() {
        creatureGenerator = CreatureGenerator()
    }

    @Test
    fun testGeneratedHitPoints() {
        val attributes = CreatureAttributes(intelligence = 7, strength = 3, endurance = 10)
        val name = "Rikachu"
        val expectedCreature = Creature(attributes, 84, name)

        assertEquals(expectedCreature, creatureGenerator.generateCreature(attributes, name))
    }

}
