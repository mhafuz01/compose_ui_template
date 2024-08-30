package dev.mhafuz.uitemplate.ui.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.util.tab.PrimaryTab
import dev.mhafuz.uitemplate.util.tab.SecondaryTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryTabs(index: Int, selectedTab: PrimaryTab, onTabClick: (SecondaryTab) -> Unit) {
    SecondaryTabRow(selectedTabIndex = index) {
        SecondaryTab.entries.forEachIndexed { index, tab ->
            SecondaryTab(
                tab = tab, onTabClick = onTabClick,
                selected = (index == selectedTab.ordinal)
            )
        }
    }
}

@Composable
fun SecondaryTab(selected: Boolean, tab: SecondaryTab, onTabClick: (SecondaryTab) -> Unit) {
    Tab(selected = selected, onClick = { onTabClick(tab) }) {
        Box(Modifier.height(48.dp), contentAlignment = Alignment.Center) {
            Text(tab.name, style = typography.titleSmall)
        }
    }
}