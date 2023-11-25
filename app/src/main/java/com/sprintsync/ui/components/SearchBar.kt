package com.sprintsync.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R

@Composable
fun SearchBar(placeHolder: String, onValueChange: ((String) -> Unit)? = null) {
    var searchTerm by remember { mutableStateOf("") }

    TextField(
        value = searchTerm,
        onValueChange = {
            searchTerm = it
            onValueChange?.invoke(it)
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeHolder, style = MaterialTheme.typography.labelLarge) },
        leadingIcon = {
            Icon(painterResource(id = R.drawable.search), contentDescription = "search bar")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = 24.dp)
    )
}