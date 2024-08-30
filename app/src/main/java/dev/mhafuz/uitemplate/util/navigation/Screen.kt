package dev.mhafuz.uitemplate.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val text: String, val icon: ImageVector) {
    Inbox("Inbox", Icons.Default.Inbox),
    Outbox("Outbox", Icons.AutoMirrored.Default.Send),
    Favorite("Favorite", Icons.Default.Favorite),
    Trash("Trash", Icons.Default.Delete)
}