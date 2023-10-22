package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.ExpandTextField
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
	Surface {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 24.dp)
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
				Column {
					Text(
						text = "Hi, Welcome Back! ",
						style = TextStyle(
							fontSize = 24.sp,
							color = Purple40,
							fontWeight = FontWeight(800),
						),
					)
					Text(
						text = "Hello again, we missed you <3",
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
				ExpandTextField(
					label = "Email",
					placeholder = "Please Enter Your Email",
					modifier = Modifier.fillMaxWidth(),
					leadingIcon = {
						Image(
							painter = painterResource(id = R.drawable.email),
							contentDescription = null,
							contentScale = ContentScale.Fit
						)
					}
				)

				ExpandTextField(
					type = "hidden",
					label = "Password",
					placeholder = "Please Enter Your Password",
					modifier = Modifier.fillMaxWidth(),
					leadingIcon = {
						Image(
							painter = painterResource(id = R.drawable.key),
							contentDescription = null,
							contentScale = ContentScale.Fit
						)
					},
					trailingIcon = {
						Image(
							painter = painterResource(id = R.drawable.visibility),
							contentDescription = null,
							contentScale = ContentScale.Fit
						)
					}
				)
				Box(
					modifier = Modifier.fillMaxWidth(),
					contentAlignment = Alignment.BottomEnd
				) {
					Text(
						modifier = Modifier.clickable(
							interactionSource = MutableInteractionSource(),
							indication = rememberRipple(
								bounded = true,
								radius = 250.dp
							),
							onClick = {}
						),
						text = "Forgot Password",
						color = Red80
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
				Column(
					modifier = Modifier
						.fillMaxWidth(),
					verticalArrangement = Arrangement.spacedBy(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CustomButton(
						type = "filled",
						text = "Login",
						surfaceModifier = Modifier.fillMaxWidth()
					)
					Row(
						Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically
					) {
						Box(
							modifier = Modifier
								.height(2.dp)
								.weight(1f)
								.background(Purple40)
						)
						ClickableText(
							modifier = Modifier.weight(1f),
							text = AnnotatedString("Or continue with"),
							style = TextStyle(
								textAlign = TextAlign.Center,
								color = Purple40
							),
							onClick = { /*TODO*/ },
						)
						Box(
							modifier = Modifier
								.height(2.dp)
								.weight(1f)
								.background(Purple40)
						)
					}
					Row(
						modifier = Modifier
							.fillMaxWidth()
							.wrapContentHeight(),
						horizontalArrangement = Arrangement.spacedBy(20.dp)
					) {
						CustomButton(
							type = "outlined",
							text = "Google",
							modifier = Modifier.fillMaxWidth(),
							surfaceModifier = Modifier.weight(1f),
							icon = {
								Image(
									modifier = Modifier.size(ButtonDefaults.IconSize),
									painter = painterResource(id = R.drawable.google),
									contentDescription = null,
									contentScale = ContentScale.Fit
								)
							}
						)

					}
				}
			}
			Row(
				modifier = Modifier
					.fillMaxSize()
					.weight(0.5f),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center
			) {
				Text(text = "Don't have an account?")
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
					text = "Sign Up",
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
private fun LoginPreview() {
	SprintSyncTheme {
		SignInScreen(Modifier)
	}
}