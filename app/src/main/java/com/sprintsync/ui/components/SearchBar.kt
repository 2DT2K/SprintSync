package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sprintsync.R

@Composable
fun SearchBar(placeHolder: String, onValueChange: ((String) -> Unit)? = null) {
	var searchTerm by remember { mutableStateOf("") }

	ExpandTextField(
		modifier = Modifier.fillMaxWidth(),
		value = searchTerm,
		label = "",
		placeholder = placeHolder,
		onValueChange = {
			searchTerm = it
			if (onValueChange != null) {
				onValueChange(searchTerm)
			}
		},
		leadingIcon = {
			Image(
				painter = painterResource(id = R.drawable.search),
				contentDescription = null,
				contentScale = ContentScale.Fit
			)
		},
		colors = OutlinedTextFieldDefaults.colors(
			focusedBorderColor = Color.Transparent,
			unfocusedBorderColor = Color.Transparent,
		)
	)
}