package dev.mhafuz.uitemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.mhafuz.uitemplate.ui.tab.PrimaryTabScreen
import dev.mhafuz.uitemplate.ui.tab.PrimaryTabs
import dev.mhafuz.uitemplate.ui.theme.UiTemplateTheme
import dev.mhafuz.uitemplate.util.tab.PrimaryTab

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiTemplateTheme {
                PrimaryTabScreen(Modifier.safeContentPadding())
            }
        }
    }
}

@Composable
fun SecondaryTabScreen(modifier: Modifier = Modifier) {
    var selectedTab by rememberSaveable { mutableStateOf(PrimaryTab.FLIGHT) }
    Column(modifier.safeContentPadding()) {
        PrimaryTabs(
            selectedIndex = selectedTab.ordinal,
            onTabClick = { selectedTab = it }
        )
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = selectedTab.icon, contentDescription = selectedTab.label)
            Text(text = selectedTab.label, style = typography.titleSmall)
        }
    }
}
