package dev.mhafuz.uitemplate.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mhafuz.uitemplate.R

@Composable
fun OneLineItem(modifier: Modifier = Modifier, labelText: String) {
    Box(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp, 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = labelText, style = typography.bodyLarge)
    }
}

@Composable
fun OneLineItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    labelText: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = leadingIcon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = labelText, style = typography.bodyLarge)
    }
}

@Composable
fun ProfileItem(modifier: Modifier = Modifier, labelText: String) {
    var checked by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(40.dp)
                .background(color = colorScheme.secondaryContainer, shape = CircleShape),
            contentAlignment = Alignment.Center,
            content = { Text(text = "A") }
        )
        Text(
            text = labelText,
            style = typography.bodyLarge,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
fun ArticleItem(modifier: Modifier = Modifier, labelText: String) {
    var checked by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier
            .fillMaxWidth()
//            .height(56.dp)
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
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
        Text(
            text = labelText,
            style = typography.bodyLarge,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }
}

@Preview(showBackground = true)
@Composable
private fun OneLineListsPrev() {
    OneLineItem(labelText = "Headline")
}

@Preview(showBackground = true)
@Composable
private fun OneLineListsPrev2() {
    OneLineItem(leadingIcon = Icons.Default.Person, labelText = "Headline")
}

@Preview(showBackground = true)
@Composable
private fun OneLineListsPrev3() {
    ProfileItem(labelText = "Headline")
}

@Preview(showBackground = true)
@Composable
private fun OneLineListsPrev4() {
    ArticleItem(labelText = "Headline")
}

