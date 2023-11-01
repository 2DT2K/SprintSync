package com.sprintsync.ui.views.auth

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.auth.EmailField
import com.sprintsync.ui.components.auth.PasswordField
import com.sprintsync.ui.components.auth.SignUpButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpView(
	modifier: Modifier = Modifier,
	signUpWithPassword: (String, String) -> Unit = { _, _ -> },
	signIn: () -> Unit = {},
) {
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
					modifier = modifier.requiredSize(240.dp),
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
				EmailField { email = it }

				PasswordField { password = it }

				PasswordField(isNormal = false) { confirmPassword = it }
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
						signUpWithPassword = { signUpWithPassword(email, password) },
						signIn = signIn
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
