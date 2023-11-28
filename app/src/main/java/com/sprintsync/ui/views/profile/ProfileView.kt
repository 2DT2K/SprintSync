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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.data.auth.Authenticator
import com.sprintsync.data.view_models.AuthViewModel
import com.sprintsync.ui.components.profile.ProfileSettingGroup
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

@Composable
fun ProfileScreen(navController: NavController? = null) {
	val user = Authenticator.signedInUser
	val authVM = hiltViewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

	LaunchedEffect(authState) {
		if (!authState.signedIn) navController?.navigate(Screens.Signin.route)
	}

	val account = listOf(
		Setting(R.drawable.edit, "Edit profile"),
		Setting(R.drawable.notification_oulined, "Notifications"),
		Setting(R.drawable.dark_mode, "Turn on dark mode"),
		Setting(R.drawable.language, "Language")
	)

	val supportAndAbout = listOf(
		Setting(R.drawable.help_outline, "Help and Support"),
		Setting(R.drawable.policy, "Term and Policies"),
	)

	val actions = listOf(
		Setting(R.drawable.flag, "Report a problem"),
		Setting(R.drawable.logout, "Log out") { authVM.signOut() },
	)

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
						text = user?.name ?: "",
						style = MaterialTheme.typography.displaySmall,
						color = MaterialTheme.colorScheme.onSurface
					)
				}

				Box(modifier = Modifier) {
					Text(
						text = user?.email ?: "",
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
				}
			}
		}

		Divider(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.smallMedium))

		ProfileSettingGroup(title = "Account", settings = account)

		ProfileSettingGroup(title = "Support & About", settings = supportAndAbout)

		ProfileSettingGroup(title = "Actions", settings = actions)
	}
}

@Preview
@Composable
fun PreviewProfileScreen() {
	SprintSyncTheme { ProfileScreen() }
}

data class Setting(
	val icon: Int,
	val settingName: String,
	val onClick: () -> Unit = {}
)