package com.raywenderlich.android.creaturemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllCreaturesViewModel(private val creatureRepository: CreatureRepository = RoomRepository()) : ViewModel() {

    private val _creaturesLiveData = creatureRepository.getAllCreatures()

    val creaturesLiveData: LiveData<List<Creature>>
        get() = _creaturesLiveData

    fun clearAllCreatures() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                creatureRepository.clearAllCreatures()
            }
        }
    }

}