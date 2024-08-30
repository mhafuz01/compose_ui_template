package dev.mhafuz.uitemplate.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavItem(val icon: ImageVector, val title: String) {
    Home(Icons.Default.Home, "Home"),
    Profile(Icons.Default.Person, "Profile"),
    Premium(Icons.Default.Diamond, "Premium"),
    Settings(Icons.Default.Settings, "Settings")
}