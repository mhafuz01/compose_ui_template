package dev.mhafuz.uitemplate.ui.list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.R

@Composable
fun List(modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(count = 3) {
            SwipeAbleListItem(labelText = "HeadLine")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeAbleListItem(modifier: Modifier = Modifier, labelText: String) {
    val state = rememberSwipeToDismissBoxState()
    val size by animateDpAsState(
        targetValue = if (state.currentValue == state.targetValue) 0.dp else 24.dp, label = ""
    )
    SwipeToDismissBox(state = state,
        enableDismissFromStartToEnd = false,
        backgroundContent = { BackgroundContent(size = size) }) {
        Column(
            modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(colorScheme.surface)
        ) {
            Row(Modifier.padding(16.dp, 8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(56.dp)
                        .background(color = Color.LightGray, shape = CutCornerShape(0.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_category),
                        null,
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    text = labelText,
                    style = typography.bodyLarge,
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 16.dp)
                )
                Icon(painterResource(id = R.drawable.ic_more_horizontal), null)
            }
            HorizontalDivider()
        }
    }
}

@Composable
fun BackgroundContent(modifier: Modifier = Modifier, size: Dp) {
    Box(
        modifier
            .fillMaxSize()
            .background(colorScheme.error)
            .padding(end = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(Icons.Default.Delete, null, tint = colorScheme.onError, modifier = Modifier.size(size))
    }
}

@Preview(showBackground = true)
@Composable
private fun ListPreview() {
    SwipeAbleListItem(labelText = "Headline")
}

