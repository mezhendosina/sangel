package ru.sangel.zaya.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sangel.zaya.data.map.MapRepository
import ru.sangel.zaya.data.settings.AppPrefs

class MainViewModel(
    private val mapRepository: MapRepository,
    private val appPrefs: AppPrefs,
) : ViewModel() {
    fun updateLocation(
        latitude: Double,
        longtude: Double,
    ) {
        viewModelScope.launch {
            if (appPrefs.isAuthorized()) {
                mapRepository.updateLocation(
                    latitude,
                    longtude,
                )
            }
        }
    }
}
