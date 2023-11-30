package com.sprintsync.ui.views.profile.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.profile.edit_profile.CountryDropDownMenu
import com.sprintsync.ui.components.profile.edit_profile.DateWheelPicker
import com.sprintsync.ui.components.profile.edit_profile.EditProfileAvatarGroup
import com.sprintsync.ui.components.profile.edit_profile.ProfileInfoCard
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun EditProfile() {
	val userInfo = listOf("Name", "Email", "Password")
	var userName by remember { mutableStateOf("") }
	var userEmail by remember { mutableStateOf("") }
	var userPassword by remember { mutableStateOf("") }
	var userDateOfBirth by remember { mutableStateOf("") }
	var userCountry by remember { mutableStateOf("") }


	Column(
		modifier = Modifier.verticalScroll(rememberScrollState()),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		EditProfileAvatarGroup()

		ProfileInfoCard(
			title = "Name",
			value = userName,
			onValueChange = { userName = it }
		)

		ProfileInfoCard(
			title = "Email",
			value = userEmail,
			onValueChange = { userEmail = it }
		)

		ProfileInfoCard(
			title = "Password",
			value = userPassword,
			onValueChange = { userPassword = it }
		)

		ProfileInfoCard(
			title = "Date of Birth",
			value = userDateOfBirth,
			content = {
				DateWheelPicker {
					userDateOfBirth = it
				}
			},
		)

		ProfileInfoCard(
			title = "Country/Region",
			value = userCountry,
			content = {
				CountryDropDownMenu {
					userCountry = it
				}
			},
		)
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewEditProfile() {
	SprintSyncTheme {
		EditProfile()
	}
}