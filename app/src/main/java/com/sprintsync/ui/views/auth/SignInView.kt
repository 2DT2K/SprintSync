package com.sprintsync.ui.views.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.data.view_models.AuthViewModel
import com.sprintsync.ui.components.auth.EmailField
import com.sprintsync.ui.components.auth.PasswordField
import com.sprintsync.ui.components.auth.PromptRow
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignInView(navController: NavController? = null) {
	val authVM = hiltViewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	var emailError by remember { mutableStateOf("") }
	var passwordError by remember { mutableStateOf("") }

	val launcher = rememberLauncherForActivityResult(
		ActivityResultContracts.StartIntentSenderForResult()
	) {
		if (it.resultCode == RESULT_OK) authVM.signIn(it.data)
	}

	LaunchedEffect(authState) {
		if (authState.signedIn) navController?.navigate(Screens.Homepage.route)
//		TODO: Add error handling
	}

	Surface {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(start = 24.dp, end = 24.dp)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.defaultMinSize()
					.weight(0.7f),
				contentAlignment = Alignment.TopCenter
			) {
				Image(
					modifier = Modifier.requiredSize(240.dp),
					painter = painterResource(id = R.drawable.logo),
					contentDescription = null
				)
			}

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.3f)
			) {
				Title(
					title = "Hi, Welcome Back! ",
					subtitle = "Hello again, we missed you <3"
				)
			}

			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {
				EmailField(
					onValueChange = { email = it },
					errorText = emailError,
					isError = emailError.isNotEmpty()
				)

				PasswordField(
					onValueChange = { password = it },
					errorText = passwordError,
					isError = passwordError.isNotEmpty()
				)

				Box(
					modifier = Modifier.fillMaxWidth(),
					contentAlignment = Alignment.BottomEnd
				) {
					PromptRow(
						modifier = Modifier,
						normalText = "Forgot Password?",
						highlightedText = "Reset",
						highlightColor = MaterialTheme.colorScheme.error,
						onClick = { navController?.navigate(Screens.PasswordReset.route) }
					)
				}
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.8f),
				verticalArrangement = Arrangement.Bottom,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				SignInButtonGroup(
					signInWithPassword = { authVM.signIn(email, password) },
					signInWithGoogle = { authVM.signIn(launcher) },
					signUp = { navController?.navigate(Screens.Signup.route) }
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
	SprintSyncTheme { SignInView() }
}
