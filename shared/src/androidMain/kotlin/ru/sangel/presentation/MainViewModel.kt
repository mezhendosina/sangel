package ru.sangel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.settings.AppPrefs

class MainViewModel(
    private val mapRepository: MapRepository,
    private val appPrefs: AppPrefs,
) : ViewModel() {
    fun updateLocation(
        latitude: Double,
        longtude: Double,
    ) {
        viewModelScope.launch {
            if (appPrefs.isAuthtorized()) {
                mapRepository.updateLocation(
                    latitude,
                    longtude,
                )
            }
        }
    }
}
