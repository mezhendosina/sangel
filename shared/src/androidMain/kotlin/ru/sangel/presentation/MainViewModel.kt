package ru.sangel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sangel.app.data.map.MapKitRepository

class MainViewModel(
    private val mapKitRepository: MapKitRepository,
) : ViewModel() {
    fun updateLocation(
        latitude: Double,
        longtude: Double,
    ) {
        viewModelScope.launch {
            mapKitRepository.updateLocation(latitude, longtude)
        }
    }
}
