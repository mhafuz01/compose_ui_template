package dev.mhafuz.uitemplate.ui.navigation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.util.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    headLine: String? = "Mail",
    headLine2: String? = "Labels"
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var screenState by remember { mutableStateOf(Screen.Inbox) }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                headLine?.let {
                    NavigationDrawerItem(text = it)
                }
                Screen.entries.forEach {
                    NavigationDrawerItem(
                        label = { Text(text = it.text) },
                        icon = { Icon(imageVector = it.icon, contentDescription = it.text) },
                        selected = (screenState == it),
                        onClick = {
                            screenState = it
                            coroutineScope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                HorizontalDivider(Modifier.padding(28.dp, 16.dp))
                headLine2?.let {
                    NavigationDrawerItem(text = it)
                }
                NavigationDrawerItem(
                    label = { Text(text = "Labels") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Folder,
                            contentDescription = "Labels"
                        )
                    },
                    selected = false,
                    onClick = { },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        drawerState = drawerState,
    ) {
        when (screenState) {
            Screen.Inbox -> Screen(icon = Screen.Inbox.icon, text = Screen.Inbox.text, modifier)
            Screen.Outbox -> Screen(
                icon = Screen.Outbox.icon,
                text = Screen.Outbox.text,
                modifier
            )

            Screen.Favorite -> Screen(
                icon = Screen.Favorite.icon,
                text = Screen.Favorite.text,
                modifier
            )

            Screen.Trash -> Screen(
                icon = Screen.Trash.icon,
                text = Screen.Trash.text,
                modifier
            )
        }

    }
}

@Composable
fun NavigationDrawerItem(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 28.dp),
        contentAlignment = Alignment.CenterStart,
        content = { Text(text = text, style = typography.titleSmall) }
    )
}

@Composable
fun Screen(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text)
    }
}