package com.sprintsync.ui.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.Purple40

@Composable
fun SignInButtonGroup(
	signInWithPassword: () -> Unit = {},
	signInWithGoogle: () -> Unit = {},
	signUp: () -> Unit = {}
) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.spacedBy(20.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		CustomButton(
			surfaceModifier = Modifier
				.fillMaxWidth()
				.height(48.dp),
			onClick = signInWithPassword,
			text = "Sign In",
			fontSize = 18.sp,
			shape = RoundedCornerShape(24)
		)

		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				modifier = Modifier
					.height(2.dp)
					.weight(1f)
					.background(Purple40)
			)

			Text(
				modifier = Modifier.weight(1f),
				text = "Or With",
				style = TextStyle(
					textAlign = TextAlign.Center,
					color = Purple40
				),
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
				modifier = Modifier.fillMaxWidth(),
				surfaceModifier = Modifier.height(48.dp),
				onClick = signInWithGoogle,
				isFilled = false,
				text = "Google",
				fontSize = 18.sp,
				shape = RoundedCornerShape(24),
				icon = {
					Icon(
						painter = painterResource(id = R.drawable.google),
						contentDescription = null,
						tint = Color.Unspecified
					)
				}
			)
		}

		PromptRow(
			normalText = "Don't have an account?",
			highlightedText = "Sign Up",
			highlightColor = Purple40,
			onClick = signUp
		)
	}
}
