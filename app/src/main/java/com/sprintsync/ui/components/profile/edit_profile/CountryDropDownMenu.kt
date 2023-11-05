package com.sprintsync.ui.components.profile.edit_profile

import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.views.profile.edit_profile.countryList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropDownMenu(onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countryList[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
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

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            LazyColumn(
                modifier = Modifier
                    .size(
                        width = 320.dp,
                        height = 560.dp
                    )
            ) {
                items(countryList) { country ->
                    DropdownMenuItem(
                        modifier = Modifier.requiredSizeIn(maxHeight = 200.dp),
                        text = { CustomText(text = country) },
                        onClick = {
                            onValueChange(selectedCountry)
                            selectedCountry = country
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}
