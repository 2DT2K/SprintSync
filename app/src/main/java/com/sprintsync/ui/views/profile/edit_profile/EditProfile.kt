package com.sprintsync.ui.views.profile.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.theme.SprintSyncTheme

data class Setting(val icon: Int, val settingName: String)

@Composable
fun EditProfile() {
    Column {
        Row {

        }
    }
}

@Preview
@Composable
fun PreviewEditProfile() {
    SprintSyncTheme {
        EditProfile()
    }
}