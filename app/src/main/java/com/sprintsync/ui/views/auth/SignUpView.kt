package com.sprintsync.ui.views.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.sprintsync.ui.components.auth.SignUpButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpView(navController: NavController? = null) {
	val authVM = hiltViewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }

	var emailError by remember { mutableStateOf("") }
	var passwordError by remember { mutableStateOf("") }
	var confirmPasswordError by remember { mutableStateOf("") }

	LaunchedEffect(authState) {
		if (authState.signedIn) navController?.navigate(Screens.VerifyAccount.route)
		else {
			Log.e("SignUpView", authState.errorMessage.toString())
			emailError = "Invalid email"
			passwordError = "Invalid password"
		}
	}

	LaunchedEffect(email, password, confirmPassword) {
		emailError = ""
		passwordError = ""
		confirmPasswordError = ""
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
					.weight(0.6f),
				contentAlignment = Alignment.Center
			) {
				Image(
					modifier = Modifier.requiredSize(240.dp),
					painter = painterResource(id = R.drawable.logo),
					contentDescription = "LOGO"
				)
			}
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.3f)
			) {
				Title(
					title = "Create an account ",
					subtitle = "Join us today !"
				)
			}

			Column(
				modifier = Modifier.wrapContentHeight(),
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
					isError = emailError.isNotEmpty()
				)

				PasswordField(
					isNormal = false,
					onValueChange = { confirmPassword = it },
					errorText = confirmPasswordError,
					isError = confirmPasswordError.isNotEmpty()
				)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.75f),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Spacer(modifier = Modifier.weight(0.5f))

				Surface(modifier = Modifier.weight(1f)) {
					SignUpButtonGroup(
						signUp = {
							if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword == password) {
								authVM.signUp(email, password)
								return@SignUpButtonGroup
							}
							when {
								email.isEmpty() -> emailError = "Email is required"
								password.isEmpty() -> passwordError = "Password is required"
								confirmPassword.isEmpty() -> confirmPasswordError = "Confirm Password is required"
								confirmPassword != password -> confirmPasswordError = "Password does not match"
							}
						},
						signIn = { navController?.popBackStack() }
					)
				}
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
	SprintSyncTheme { SignUpView() }
}
