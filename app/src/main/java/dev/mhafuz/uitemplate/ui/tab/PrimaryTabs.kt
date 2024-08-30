package dev.mhafuz.uitemplate.ui.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.util.tab.PrimaryTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryTabScreen(modifier: Modifier = Modifier) {
    val state = rememberPagerState { PrimaryTab.entries.size }
    val scope = rememberCoroutineScope()

    Column(modifier) {
        PrimaryTabs(
            selectedIndex = state.currentPage,
            onTabClick = {
                scope.launch {
                    state.animateScrollToPage(it.ordinal)
                }
            }
        )
        HorizontalPager(state = state) { page ->
                when (page) {
                    0 -> PageScreen(icon = Icons.Default.Flight, text = "Flight")
                    1 -> PageScreen(icon = Icons.Default.ShoppingBag, text = "Trips")
                    2 -> PageScreen(icon = Icons.Default.Explore, text = "Explore")
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTabs(selectedIndex: Int, onTabClick: (PrimaryTab) -> Unit) {
    PrimaryTabRow(selectedTabIndex = selectedIndex) {
        PrimaryTab.entries.forEachIndexed { index, tab ->
            PrimaryTab(
                tab = tab, onTabClick = onTabClick,
                selected = (index == selectedIndex)
            )
        }
    }
}

@Composable
fun PrimaryTab(selected: Boolean, tab: PrimaryTab, onTabClick: (PrimaryTab) -> Unit) {
    Tab(
        selected = selected,
        onClick = { onTabClick(tab) },
        unselectedContentColor = colorScheme.onSurfaceVariant,
        selectedContentColor = colorScheme.primary
    ) {
        Column(
            Modifier.height(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = tab.icon, contentDescription = tab.label)
            Text(text = tab.label, style = typography.titleSmall)
        }
    }
}

@Composable
fun PageScreen(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Text(text = text, style = typography.titleSmall)
    }
}



