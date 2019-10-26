package com.raywenderlich.android.creaturemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class AllCreaturesViewModel(private val creatureRepository: CreatureRepository = RoomRepository()) : ViewModel() {

    private val _creaturesLiveData = creatureRepository.getAllCreatures()

    val creaturesLiveData: LiveData<List<Creature>> = _creaturesLiveData

    fun clearAllCreatures() {
        creatureRepository.clearAllCreatures()
    }

}