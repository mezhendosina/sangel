package ru.sangel.zaya.presentation.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.launch
import ru.sangel.zaya.data.map.MapRepository
import ru.sangel.zaya.domain.MapUseCase

class MapViewModel(
    private val mapUseCase: MapUseCase,
    private val mapRepository: MapRepository,
) : ViewModel() {
    val mapPoints =
        mapUseCase.mapPoints
    val zoom = mapRepository.zoom

    fun updateLocation(
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            mapRepository.updateLocation(latitude, longitude)
        }
    }

    fun setZoom(zoom: Float) {
        mapRepository.setZoom(zoom)
    }

    fun getCameraPosition(): CameraPosition? = mapRepository.cameraPosition.value

    fun updateCamera(cameraPosition: CameraPosition) {
        mapRepository.updateCameraPosition(cameraPosition)
    }

    fun checkPermission(context: Context): Boolean {
        val permission = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        return permission == PackageManager.PERMISSION_GRANTED
    }
}
