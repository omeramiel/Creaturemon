package com.raywenderlich.android.creaturemon.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.*
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class CreatureViewModel(private val creatureGenerator: CreatureGenerator = CreatureGenerator(),
                        private val creatureRepository: CreatureRepository = RoomRepository()) : ViewModel() {

    private val _creatureLiveData = MutableLiveData<Creature>()
    val creatureLiveData: LiveData<Creature> = _creatureLiveData

    private val _saveCreatureLiveData = MutableLiveData<Boolean>()
    val saveCreatureLiveData: LiveData<Boolean> = _saveCreatureLiveData

    var name = ObservableField<String>("")
    var drawable = 0
    var intelligence = 0
    var strength = 0
    var endurance = 0
    lateinit var creature: Creature

    fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = creatureGenerator.generateCreature(name = name.get().orEmpty(), drawable = drawable, attributes = attributes)
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

    fun saveCreature() =
            if (canSaveCreature()) {
                updateCreature()
                creatureRepository.saveCreature(creature)
                _saveCreatureLiveData.postValue(true)
            } else {
                _saveCreatureLiveData.postValue(false)
            }

    fun canSaveCreature(): Boolean {
        val name = name.get().orEmpty()
        return name.isNotBlank() && drawable != 0 && intelligence != 0 && strength != 0 && endurance != 0
    }

}