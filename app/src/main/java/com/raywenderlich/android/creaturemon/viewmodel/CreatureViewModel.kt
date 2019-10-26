package com.raywenderlich.android.creaturemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.*
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class CreatureViewModel(private val creatureGenerator: CreatureGenerator = CreatureGenerator(),
                        private val creatureRepository: CreatureRepository = RoomRepository()) : ViewModel() {

    private val _creatureLiveData = MutableLiveData<Creature>()

    val creatureLiveData: LiveData<Creature> = _creatureLiveData

    var name = ""
    var intelligence = 0
    var strength = 0
    var endurance = 0
    var drawable = 0

    lateinit var creature: Creature

    fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = creatureGenerator.generateCreature(name = name, drawable = drawable, attributes = attributes)
        _creatureLiveData.postValue(creature)
    }

    fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE -> intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH -> strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE -> endurance = AttributeStore.ENDURANCE[position].value
        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        updateCreature()
    }

    fun saveCreature(): Boolean =
            if (canSaveCreature()) {
                creatureRepository.saveCreature(creature)
                true
            } else {
                false
            }

    fun canSaveCreature(): Boolean {
        return name.isNotBlank() && drawable != 0 && intelligence != 0 && strength != 0 && endurance != 0
    }

}