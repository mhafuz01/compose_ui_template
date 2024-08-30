package dev.mhafuz.uitemplate.util.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

enum class PrimaryTab(val icon: ImageVector, val label: String) {
    FLIGHT(Icons.Default.Flight, "Flight"),
    TRIPS(Icons.Default.ShoppingBag, "Trips"),
    EXPLORE(Icons.Default.Explore, "Explore")
}