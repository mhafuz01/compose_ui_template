package dev.mhafuz.uitemplate.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.R

@Composable
fun ThreeLineItem(
    modifier: Modifier = Modifier,
    labelText: String = "Headline",
    supportingText: String = "Supporting text that is long enough to fill up multiple lines"
) {
    Column(
        modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp, 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = labelText, style = typography.bodyLarge)
        Text(
            text = supportingText,
            style = typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ThreeLineItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    labelText: String = "Headline",
    supportingText: String = "Supporting text that is long enough to fill up multiple lines"
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp, 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector = leadingIcon, contentDescription = null)
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = labelText, style = typography.bodyLarge)
            Text(
                text = supportingText,
                style = typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ProfileItem2(
    modifier: Modifier = Modifier,
    labelText: String = "Headline",
    supportingText: String = "Supporting text that is long enough to fill up multiple lines"
) {
    var checked by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp, 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier
                .size(40.dp)
                .background(color = colorScheme.primaryContainer, shape = CircleShape),
            contentAlignment = Alignment.Center,
            content = { Text(text = "A") }
        )
        Column(Modifier.weight(1F), verticalArrangement = Arrangement.Center) {
            Text(text = labelText, style = typography.bodyLarge)
            Text(
                text = supportingText,
                style = typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
fun ArticleItem2(
    modifier: Modifier = Modifier,
    labelText: String = "Headline",
    supportingText: String = "Supporting text that is long enough to fill up multiple lines",
    trailingText: String = "100+"
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(16.dp, 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier
                .size(56.dp)
                .background(color = Color.LightGray, shape = CutCornerShape(0.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(id = R.drawable.ic_category), null,
                tint = Color.Gray,
                modifier = Modifier.size(28.dp)
            )
        }
        Column(Modifier.weight(1F), verticalArrangement = Arrangement.Center) {
            Text(text = labelText, style = typography.bodyLarge)
            Text(
                text = supportingText,
                style = typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(text = trailingText, style = typography.labelSmall)
    }
}


@Preview(showBackground = true)
@Composable
private fun ThreeLineListsPrev() {
    ThreeLineItem()
}

@Preview(showBackground = true)
@Composable
private fun ThreeLineListsPrev2() {
    ThreeLineItem(leadingIcon = Icons.Default.Person, labelText = "Headline")
}

@Preview(showBackground = true)
@Composable
private fun ThreeLineListsPrev3() {
    ProfileItem2()
}

@Preview(showBackground = true)
@Composable
private fun ThreeLineListsPrev4() {
    ArticleItem2()
}