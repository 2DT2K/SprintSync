package com.sprintsync.ui.components.profile.edit_profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomModalBottomSheet
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.views.profile.edit_profile.countryList
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropDownMenu(onValueChange: (String) -> Unit) {
	var expanded by remember { mutableStateOf(false) }
	var selectedCountry by remember { mutableStateOf(countryList[0]) }
	var searchTerm by remember { mutableStateOf("") }

	Box(modifier = Modifier.fillMaxWidth()) {
		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = selectedCountry,
			onValueChange = {},
			shape = RoundedCornerShape(16),
			colors = OutlinedTextFieldDefaults.colors(
				focusedBorderColor = Purple40,
				unfocusedBorderColor = Purple40
			),
			readOnly = true,
			trailingIcon = {
				Icon(
					modifier = Modifier
						.size(28.dp),
					painter = painterResource(
						id = if (expanded) R.drawable.arrow_down_2
						else R.drawable.arrow_up
					),
					contentDescription = null
				)
			}
		)

		Box(
			modifier = Modifier
				.matchParentSize()
				.alpha(0f)
				.clickable { expanded = !expanded },
		)
	}

	CustomModalBottomSheet(
		modifier = Modifier.fillMaxHeight(),
		isSheetShown = expanded,
		changeVisibility = { expanded = it },
		sheetContent = {
			SearchBar(
				placeHolder = "Please enter your country",
				onValueChange = { searchTerm = it }
			)

			LazyColumn {
				items(countryList) { country ->
					if (country
							.lowercase(Locale.ROOT)
							.contains(searchTerm)
					) DropdownMenuItem(
						modifier = Modifier.requiredSizeIn(maxHeight = 200.dp),
						text = { Text(text = country) },
						onClick = {
							onValueChange(selectedCountry)
							selectedCountry = country
							expanded = false
						},
					)
				}
			}
		}
	)
}

