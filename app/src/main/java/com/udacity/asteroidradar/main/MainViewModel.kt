package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val pictureOfDayLiveData: LiveData<PictureOfDay>
        get() = mainRepository.pictureOfDay

    val asteroidLiveData: LiveData<List<Asteroid>>
        get() = mainRepository.asteroids

    private val database = getDatabase(application)
    private val mainRepository = MainRepository(database)


    init {
        viewModelScope.launch {
            mainRepository.refreshAsteroids()
            mainRepository.refreshPictureOfTheDay()
        }
    }
}