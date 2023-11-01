package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.auth.Email
import com.sprintsync.ui.components.auth.Password
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignInView(
	modifier: Modifier = Modifier,
	signInWithPassword: (String, String) -> Unit = { _, _ -> },
	signInWithGoogle: () -> Unit = {},
	resetPassword: () -> Unit = {},
	signUp: () -> Unit = {}
) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

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
					.defaultMinSize()
					.weight(0.75f),
				contentAlignment = Alignment.TopCenter
			) {
				Image(
					painter = painterResource(id = R.drawable.logo),
					contentDescription = "LOGO",
					modifier = modifier.requiredSize(240.dp)
				)
			}

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.5f)
			) {
				Title(
					title = "Hi, Welcome Back! ",
					subtitle = "Hello again, we missed you <3"
				)
			}

			Column(
				modifier = Modifier.wrapContentHeight(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {
				Email { email = it }

				Password { password = it }

				Box(
					modifier = Modifier
						.fillMaxWidth()
						.clickable(
							interactionSource = MutableInteractionSource(),
							indication = rememberRipple(
								bounded = true,
								radius = 250.dp
							),
							onClick = resetPassword
						),
					contentAlignment = Alignment.BottomEnd
				) {
					Row {
						Text(
							text = "Forgot Password?",
							color = Grey40
						)

						Spacer(modifier = Modifier.width(4.dp))

						Text(
							text = "Reset",
							color = Red80,
							fontWeight = FontWeight.Bold
						)
					}
				}
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.9f),
				verticalArrangement = Arrangement.Bottom,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				SignInButtonGroup(
					signInWithPassword = { signInWithPassword(email, password) },
					signInWithGoogle = signInWithGoogle,
					signUp = signUp
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
	SprintSyncTheme {
		SignInView()
	}
}
