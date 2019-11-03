package com.raywenderlich.android.creaturemon.di

import androidx.room.Room
import com.raywenderlich.android.creaturemon.model.CreatureGenerator
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.CreatureDatabase
import com.raywenderlich.android.creaturemon.model.room.RoomRepository
import com.raywenderlich.android.creaturemon.viewmodel.AllCreaturesViewModel
import com.raywenderlich.android.creaturemon.viewmodel.CreatureViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        AllCreaturesViewModel(get())
    }
    viewModel {
        CreatureViewModel(CreatureGenerator(), get())
    }
    single<CreatureRepository> {
        RoomRepository(get())
    }
    single {
        Room.databaseBuilder(androidContext(), CreatureDatabase::class.java, "creature_database").build()
    }
}