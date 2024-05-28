package ru.sangel.presentation.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.app.domain.MapUseCase

class MapViewModel(
    private val mapUseCase: MapUseCase,
    private val mapKitRepository: MapKitRepository,
) : ViewModel() {
    val mapPoints =
        mapUseCase.mapPoints.map {
        }
    val zoom = mapKitRepository.zoom

    fun updateLocation(
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            mapKitRepository.updateLocation(latitude, longitude)
        }
    }

    fun setZoom(zoom: Float) {
        mapKitRepository.setZoom(zoom)
    }

    fun getCameraPosition(): CameraPosition? = mapKitRepository.cameraPosition.value

    fun updateCamera(cameraPosition: CameraPosition) {
        mapKitRepository.updateCameraPosition(cameraPosition)
    }

    fun checkPermission(context: Context): Boolean {
        val permission = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        return permission == PackageManager.PERMISSION_GRANTED
    }
}
