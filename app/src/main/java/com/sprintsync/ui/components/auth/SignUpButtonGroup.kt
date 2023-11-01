package com.sprintsync.ui.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple40

@Composable
fun SignUpButtonGroup(
	signUpWithPassword: () -> Unit = {},
	signIn: () -> Unit = {}
) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceBetween
	) {
		CustomButton(
			modifier = Modifier
				.fillMaxWidth()
				.height(48.dp),
			onClick = signUpWithPassword,
			text = "Sign Up",
			fontSize = 18.sp,
			shape = RoundedCornerShape(24)
		)

		Row(
			modifier = Modifier.height(40.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			CustomText(
				text = "Already have an account ?",
				color = Grey40
			)

			Spacer(modifier = Modifier.width(5.dp))

			CustomText(
				modifier = Modifier.clickable(
					interactionSource = MutableInteractionSource(),
					indication = rememberRipple(
						bounded = true,
						radius = 250.dp
					),
					onClick = signIn
				),
				text = "Login",
				color = Purple40,
				fontWeight = FontWeight.Bold
			)
		}
	}
}