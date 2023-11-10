package com.sprintsync.ui.views.auth

import android.content.Context
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.auth.AuthViewModel
import com.sprintsync.auth.Authenticator
import com.sprintsync.ui.components.auth.EmailField
import com.sprintsync.ui.components.auth.PasswordField
import com.sprintsync.ui.components.auth.SignUpButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpView(
	context: Context? = null,
	navController: NavController? = null
) {
	val scope = rememberCoroutineScope()

	val authenticator = context?.let { Authenticator(it) }

	val authVM = viewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

	LaunchedEffect(authState.signedIn) {
		if (authState.signedIn) navController?.navigate("splash")
	}

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }

	Surface {
		// TODO: remove padding when we have main scaffold
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
					errorText = "Email is not valid"
				)

				PasswordField(
					onValueChange = { password = it },
					errorText = "Password is too short"
				)

				PasswordField(
					isNormal = false,
					onValueChange = { confirmPassword = it },
					errorText = "Password is not match"
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
						signUpWithPassword = {
							authenticator?.let {
								authVM.signUpWithPassword(scope, it, email, password)
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
	SprintSyncTheme {
		SignUpView()
	}
}
