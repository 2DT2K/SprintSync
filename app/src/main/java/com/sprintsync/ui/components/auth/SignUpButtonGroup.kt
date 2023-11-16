package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.Purple40

@Composable
fun SignUpButtonGroup(
	signUp: () -> Unit = {},
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
			onClick = signUp,
			text = "Sign Up",
			fontSize = 18.sp,
			shape = RoundedCornerShape(24)
		)

		PromptRow(
			normalText = "Already have an account?",
			highlightedText = "Sign In",
			highlightColor = Purple40,
			onClick = signIn
		)
	}
}