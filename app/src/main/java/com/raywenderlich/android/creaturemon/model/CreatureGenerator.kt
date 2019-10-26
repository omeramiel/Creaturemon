package com.raywenderlich.android.creaturemon.model

class CreatureGenerator {

    fun generateCreature(attributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature {
        val hitPoints = attributes.run { intelligence * 5 + endurance * 4 + strength * 3 }
        return Creature(attributes, hitPoints, name, drawable)
    }

}
