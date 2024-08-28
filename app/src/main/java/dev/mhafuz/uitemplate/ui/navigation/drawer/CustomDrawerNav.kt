package dev.mhafuz.uitemplate.ui.navigation.drawer

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.R
import kotlin.math.roundToInt

enum class NavItem(val icon: ImageVector, val title: String) {
    Home(Icons.Default.Home, "Home"),
    Profile(Icons.Default.Person, "Profile"),
    Premium(Icons.Default.Diamond, "Premium"),
    Settings(Icons.Default.Settings, "Settings")
}

enum class DrawerState {
    Opened, Closed
}

@Composable
fun MainScreen() {
    var drawerState by rememberSaveable { mutableStateOf(DrawerState.Closed) }
    var selectedNavItem by rememberSaveable { mutableStateOf(NavItem.Home) }
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth by remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember {
        derivedStateOf { (screenWidth / 4.5).dp }
    }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    BackHandler(drawerState.isOpened()) {
        drawerState = DrawerState.Closed
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
            .background(colorScheme.onSurface.copy(alpha = 0.05f))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CustomDrawer(
            selectedNavItem = selectedNavItem,
            navItemClick = { selectedNavItem = it },
            closeClick = { drawerState = DrawerState.Closed }
        )
        MainContent(
            Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                //.coloredShadow(color = Color.Black, alpha = 0.1f, shadowRadius = 50.dp)
                    ,
            drawerState = drawerState,
            drawerClick = { drawerState = it },
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    drawerClick: (DrawerState) -> Unit
) {
    Scaffold(
        modifier.clickable(
            enabled = (drawerState == DrawerState.Opened),
            onClick = { drawerClick(DrawerState.Closed) }
        ),
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                navigationIcon = {
                    IconButton(onClick = { drawerClick(drawerState.opposite()) }) {
                        Icon(Icons.Default.Menu, "Menu Icon")
                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center,
            content = { Text(text = "Home") }
        )
    }
}

@Composable
fun CustomDrawer(
    selectedNavItem: NavItem, navItemClick: (NavItem) -> Unit, closeClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth(fraction = 0.6f)
            .fillMaxHeight()
            .padding(14.dp, 24.dp)
    ) {
        Box {
            IconButton(closeClick) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, "Back Arrow Icon",
                    tint = colorScheme.onSurfaceVariant
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Image(
            painterResource(id = R.drawable.logo), "Zodiac Image",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(40.dp))
        NavItem.entries.toTypedArray().take(3).forEach { navigationItem ->
            NavItem(
                navItem = navigationItem,
                selected = navigationItem == selectedNavItem,
                onClick = { navItemClick(navigationItem) }
            )
        }
        Spacer(Modifier.weight(1F))
        NavItem.entries.toTypedArray().takeLast(1).forEach { navItem ->
            NavItem(
                navItem = navItem, selected = false,
                onClick = {
                    when (navItem) {
                        NavItem.Settings -> {
                            navItemClick(NavItem.Settings)
                        }

                        else -> {}
                    }
                }
            )
        }
    }
}

@Composable
fun NavItem(navItem: NavItem, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable(onClick = onClick)
            .background(
                color = if (selected) colorScheme.surfaceColorAtElevation(4.dp) else Color.Unspecified,
                shape = RoundedCornerShape(99.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            navItem.icon, "Navigation Item Icon",
            tint = if (selected) colorScheme.primary else colorScheme.onSurface
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = navItem.title,
            color = if (selected) colorScheme.primary else colorScheme.onSurface,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.coloredShadow(
    color: Color, alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp, shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp, offsetX: Dp = 0.dp
) = composed {
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha = 0f).toArgb()

    this.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent
            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
}

fun DrawerState.isOpened() = (this.name == "Opened")


fun DrawerState.opposite() =
    if (this == DrawerState.Opened) DrawerState.Closed
    else DrawerState.Opened
