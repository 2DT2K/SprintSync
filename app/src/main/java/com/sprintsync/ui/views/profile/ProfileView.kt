package com.sprintsync.ui.views.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.profile.ProfileSettingGroup
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.SprintSyncTheme

data class Setting(val icon: Int, val settingName: String)

val account = listOf(
    Setting(R.drawable.edit, "Edit profile"),
    Setting(R.drawable.notification, "Notifications"),
    Setting(R.drawable.dark_mode, "Turn on dark mode"),
    Setting(R.drawable.language, "Language")
)

val supportAndAbout = listOf(
    Setting(R.drawable.help_outline, "Help and Support"),
    Setting(R.drawable.policy, "Term and Policies"),
)

val actions = listOf(
    Setting(R.drawable.flag, "Report a problem"),
    Setting(R.drawable.logout, "Log out"),
)


@Composable
fun ProfileScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.nice_avatar),
                contentDescription = "profile avatar"
            )

            Column(
                modifier = Modifier.height(64.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Nguyễn Hải Đan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Purple20
                    )
                }

                Box(modifier = Modifier) {
                    Text(
                        text = "haidan10112003@gmail.com",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Grey40
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(horizontal = 12.dp))

        ProfileSettingGroup(title = "Account", settings = account)

        ProfileSettingGroup(title = "Support & About", settings = supportAndAbout)

        ProfileSettingGroup(title = "Actions", settings = actions)
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    SprintSyncTheme {
        ProfileScreen()
    }
}