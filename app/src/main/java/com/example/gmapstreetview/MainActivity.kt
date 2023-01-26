package com.example.gmapstreetview

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gmapstreetview.ui.theme.GMapStreetViewTheme
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.compose.streetview.rememberStreetViewCameraPositionState

class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GMapStreetViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val singapore = LatLng(1.35, 103.87)
    var isPanningEnabled by remember { mutableStateOf(false) }
    var isStreetNamesEnabled by remember { mutableStateOf(true) }
    var isUserNavigationEnabled by remember { mutableStateOf(true) }
    var isZoomGesturesEnabled by remember { mutableStateOf(true) }

    val camera = rememberStreetViewCameraPositionState()
    Box(Modifier.fillMaxSize(), Alignment.BottomStart) {
        StreetView(
            Modifier.matchParentSize(),
            cameraPositionState = camera,
            streetViewPanoramaOptionsFactory = {
                StreetViewPanoramaOptions().position(singapore)
//                StreetViewPanoramaOptions().zoomGesturesEnabled(true)
            },
            isPanningGesturesEnabled = isPanningEnabled,
            isStreetNamesEnabled= isStreetNamesEnabled,
            isUserNavigationEnabled =isUserNavigationEnabled,
            isZoomGesturesEnabled=isZoomGesturesEnabled,
            onClick = {
                Log.d(TAG, "Street view clicked")
            },
            onLongClick = {
                Log.d(TAG, "Street view long clicked ${it.tilt} ${it.bearing} ${it.toString()}")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GMapStreetViewTheme {
        Greeting("Android")
    }
}