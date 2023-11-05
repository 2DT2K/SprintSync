package com.sprintsync.ui.views.profile.edit_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.ExpandableTextField
import com.sprintsync.ui.components.profile.edit_profile.CountryDropDownMenu
import com.sprintsync.ui.components.profile.edit_profile.DateWheelPicker
import com.sprintsync.ui.components.profile.edit_profile.EditProfileAvatarGroup
import com.sprintsync.ui.components.profile.edit_profile.ProfileInfoCard
import com.sprintsync.ui.theme.Purple40
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
            value = userPassword,
            content = {
                DateWheelPicker {
                    userDateOfBirth = it
                }
            },
        )

        ProfileInfoCard(
            title = "Country/Region",
            value = userPassword,
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