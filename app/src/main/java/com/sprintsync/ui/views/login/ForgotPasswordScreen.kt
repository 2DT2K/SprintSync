package com.sprintsync.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.auth.innerShadow
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen() {
	var emailValue by remember {
		mutableStateOf("")
	}
	Surface() {
		// TODO: remove padding when we have main scaffold
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(start = 24.dp, end = 24.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(40.dp)
		) {
			Box(
				modifier = Modifier
					.weight(1.25f),
				contentAlignment = Alignment.BottomCenter
			) {
				Image(
					modifier = Modifier
						.fillMaxWidth()
						.height(400.dp),
					painter = painterResource(id = R.drawable.forgotpassword),
					contentDescription = "forgot picture"
				)
			}


			Column(
				modifier = Modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(40.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Column(modifier = Modifier.fillMaxWidth()) {
					CustomText(
						text = "Forgot password?",
						fontSize = 25.sp,
						fontWeight = FontWeight.Bold,
						color = Purple20
					)
					Box(modifier = Modifier) {
						CustomText(
							text = "Don’t worry ! It happens." +
								" Please enter your email address, we wil send " +
								"your the link to reset your password.",
							fontSize = 14.sp,
							color = Color(0xFF5B5858)
						)
					}

				}
				BasicTextField(
					modifier = Modifier.fillMaxWidth(),
					value = emailValue,
					onValueChange = { newText ->
						emailValue = newText
					},
					textStyle = androidx.compose.ui.text.TextStyle(
						color = Color(0xFF746983),
						fontSize = 16.sp
					),
					decorationBox = { innerTextField ->
						Row(
							Modifier
								.background(Color(0xFFEAEAEA), RoundedCornerShape(8.dp))
								.innerShadow(
									blur = 4.dp,
									color = Color(0xFFdbdad7),
									spread = 8.dp,
									cornersRadius = 8.dp
								)
								.padding(16.dp),
							verticalAlignment = Alignment.CenterVertically
						) {
							Icon(
								Icons.Default.MailOutline,
								contentDescription = null,
								tint = Color((0xFF6B6678))
							)
							Spacer(Modifier.width(16.dp))
							if (emailValue.isEmpty()) {
								Text(text = "Enter Your Email", color = Color(0xFF746983))
							}
							else innerTextField()
						}
					}
				)

				Button(
					modifier = Modifier
						.width(140.dp)
						.height(48.dp),
					shape = RoundedCornerShape(40),
					onClick = {},
					colors = ButtonDefaults.buttonColors(containerColor = Purple40)
				) {
					Text(
						text = "Submit",
						fontSize = 16.sp
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewForgot() {
	SprintSyncTheme {
		ForgotPasswordScreen()
	}
}