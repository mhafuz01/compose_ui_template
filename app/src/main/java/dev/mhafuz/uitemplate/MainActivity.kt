package dev.mhafuz.uitemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.mhafuz.uitemplate.ui.navigation.drawer.MainScreen
import dev.mhafuz.uitemplate.ui.theme.UiTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiTemplateTheme {
                MainScreen()
            }
        }
    }
}
