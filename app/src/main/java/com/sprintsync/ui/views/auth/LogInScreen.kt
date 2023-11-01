package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.auth.Email
import com.sprintsync.ui.components.auth.Password
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.components.auth.PromptRow
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {
	Surface() {
// TODO: remove padding when we have main scaffold reddy for login and signup
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
					painter = painterResource(id = R.drawable.logo),
					contentDescription = "LOGO",
					modifier = modifier
						.requiredSize(240.dp)
				)
			}

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.3f)
			) {
				Title(
					"Hi, Welcome Back! ",
					"Hello again, we missed you <3"
				)
			}
			Column(
				modifier = Modifier
					.wrapContentHeight(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {

				Email()
				Password()

				Box(
					modifier = Modifier
						.fillMaxWidth()
						.clickable(interactionSource = MutableInteractionSource(),
							indication = rememberRipple(
								bounded = true,
								radius = 250.dp
							),
							onClick = {}
						), contentAlignment = Alignment.BottomEnd
				) {
					PromptRow(
						normalText = "Forgot Password?",
						highlightedText = "Reset",
						highlightColor = Red80,
						onClick = {},
						modifier = Modifier.height(20.dp)
					)
				}
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.9f),
				verticalArrangement = Arrangement.Bottom,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				SignInButtonGroup()
			}

		}
	}
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
	SprintSyncTheme {
		LogInScreen(Modifier)
	}
}
