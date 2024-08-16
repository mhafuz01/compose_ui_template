package dev.mhafuz.uitemplate.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun List(modifier: Modifier = Modifier) {
    val expandedContent = @Composable {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp + 16.dp))
                Text(text = "Expanded item 1", style = typography.bodyLarge)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp + 16.dp))
                Text(text = "Expanded item 2", style = typography.bodyLarge)
            }
        }
    }
    LazyColumn(modifier) {
        items(3) {
            ExpandedListItem(expandedContent = expandedContent)
        }
    }
}

@Composable
fun ExpandedListItem(
    modifier: Modifier = Modifier,
    labelText: String = "Single-line item",
    expandedContent: @Composable () -> Unit = {}
) {
    var showExpandedContent by rememberSaveable { mutableStateOf(true) }
    Column(modifier.clickable(onClick = { showExpandedContent = !showExpandedContent })) {
        Row(
            Modifier.height(56.dp).padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            Text(
                if (showExpandedContent) labelText.plus(" expanded")
                else labelText.plus(" collapsed"),
                style = typography.bodyLarge,
                modifier = Modifier.weight(1F)
            )
            Icon(
                if (showExpandedContent) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        if (showExpandedContent) expandedContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandedListItemPrev() {
    val expandedContent = @Composable {
        Column(Modifier.padding(start = 24.dp + 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Expanded item 1",
                    style = typography.bodyLarge,
                    modifier = Modifier
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Expanded item 2", style = typography.bodyLarge)
            }
        }
    }

    ExpandedListItem(expandedContent = expandedContent)
}