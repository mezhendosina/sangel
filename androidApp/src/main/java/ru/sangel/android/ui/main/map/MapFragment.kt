package ru.sangel.android.ui.main.map

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sangel.android.R
import ru.sangel.android.databinding.FragmentMapBinding
import ru.sangel.presentation.map.MapViewModel

class MapFragment : Fragment(R.layout.fragment_map), UserLocationObjectListener {
    private var mapView: MapView? = null
    private val viewModel: MapViewModel by viewModel()
    private var binding: FragmentMapBinding? = null

    private val setupUserLocationLayer =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it)
                {
                    setupUserLocationLayer()
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView?.onStart()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        mapView = binding!!.root

        setupUserLocationLayer()
        observeMapPoints()
        observeMapZoom()
        observeMapCameraPosition()
    }

    override fun onStop() {
        val mapKit = MapKitFactory.getInstance()
        mapKit.onStop()
        mapView?.onStop()
        super.onStop()
    }

    private fun observeMapPoints() {
        val map = mapView!!.mapWindow.map
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.mapPoints.collect { points ->
//                points.forEach { point ->
//                    val placemark = map.mapObjects.addPlacemark()
//                    placemark.userData = point
//                    placemark.geometry = point.location
//                    placemark.setText(point.name)
//                    placemark.setIcon(
//                        ImageProvider.fromResource(
//                            requireContext(),
//                            R.drawable.ic_map_avatar,
//                        ),
//                    )
//                }
            }
        }
    }

    private fun observeMapCameraPosition() {
        mapView?.mapWindow?.map?.addCameraListener { map, cameraPosition, cameraUpdateReason, b ->
            if (cameraUpdateReason == CameraUpdateReason.GESTURES) {
                viewModel.setZoom(cameraPosition.zoom)
            }
        }
    }

    private fun observeMapZoom() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.zoom.collect {
                mapView?.let { mapView ->
                    val cameraPosition = mapView.mapWindow.map.cameraPosition
                    val newCameraPosition =
                        CameraPosition(
                            cameraPosition.target,
                            it,
                            cameraPosition.azimuth,
                            cameraPosition.tilt,
                        )
                    mapView.mapWindow.map.move(
                        newCameraPosition,
                        Animation(Animation.Type.SMOOTH, 1f),
                        null,
                    )
                }
            }
        }
    }

    private fun setupUserLocationLayer() {
        mapView?.let { mapView ->
            val locationPermission = viewModel.checkPermission(requireContext())
            if (!locationPermission) {
                setupUserLocationLayer.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            val mapKit = MapKitFactory.getInstance()
            val userLocation = mapKit.createUserLocationLayer(mapView.mapWindow)
            userLocation.isVisible = true

            userLocation.setObjectListener(this)
        }
    }

    override fun onObjectAdded(p0: UserLocationView) {
        p0.pin.setIcon(
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.ic_map_pin,
            ),
            IconStyle().setScale(0.5f),
        )
    }

    override fun onObjectUpdated(
        p0: UserLocationView,
        p1: ObjectEvent,
    ) {
        p0.arrow.setIcon(
            ImageProvider.fromResource(requireContext(), R.drawable.ic_map_user),
            IconStyle()
                .setScale(0.5f)
                .setRotationType(RotationType.ROTATE),
        )
    }

    override fun onObjectRemoved(p0: UserLocationView) {}
}
