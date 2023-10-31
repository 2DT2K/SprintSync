package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.ExpandableTextField
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignUpScreen(
	modifier: Modifier = Modifier
) {
	Surface {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(start = 24.dp, end = 24.dp)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.5f),
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
				Column {
					Text(
						text = "Create an account ",
						style = TextStyle(
							fontSize = 24.sp,
							color = Purple40,
							fontWeight = FontWeight(800),
						),
					)
					Text(
						text = "Join us today !",
						style = TextStyle(
							fontSize = 16.sp,
							color = Grey40
						),
					)
				}
			}

			Column(
				modifier = Modifier.wrapContentHeight(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {
				ExpandableTextField(
					modifier = Modifier.fillMaxWidth(),
					label = "Email",
					placeholder = "Enter Your Email",
					leadingIcon = {
						Icon(
							painter = painterResource(id = R.drawable.email),
							contentDescription = null,
							tint = Color(0xFF381E72)
						)
					}
				)

				ExpandableTextField(
					modifier = Modifier.fillMaxWidth(),
					isPassword = true,
					label = "Password",
					placeholder = "Enter Your Password",
					leadingIcon = {
						Icon(
							painter = painterResource(id = R.drawable.key),
							contentDescription = null,
							tint = Color(0xFF381E72)
						)
					}
				)

				ExpandableTextField(
					modifier = Modifier.fillMaxWidth(),
					isPassword = true,
					label = "Confirm Password",
					placeholder = "Confirm Your Password",
					leadingIcon = {
						Icon(
							painter = painterResource(id = R.drawable.key),
							contentDescription = null,
							tint = Color(0xFF381E72)
						)
					}
				)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(0.5f),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				CustomButton(
					modifier = Modifier.fillMaxWidth(),
					text = "Sign Up"
				)
			}

			Row(
				modifier = Modifier
					.fillMaxSize()
					.weight(0.25f),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center
			) {
				Text(text = "Already have an account?")
				Spacer(modifier = Modifier.width(5.dp))
				Text(
					modifier = Modifier.clickable(
						interactionSource = MutableInteractionSource(),
						indication = rememberRipple(
							bounded = true,
							radius = 250.dp
						),
						onClick = {}
					),
					text = "Sign In",
					style = TextStyle(
						fontSize = 14.sp,
						fontWeight = FontWeight(400),
						color = Color(0xFF160062),
					)
				)
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
	SprintSyncTheme {
		SignUpScreen()
	}
}
