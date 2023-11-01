package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sprintsync.R
import com.sprintsync.ui.components.ExpandableTextField

@Composable
fun Email(
	onValueChange: ((String) -> Unit)? = null
) {
	ExpandableTextField(
		modifier = Modifier.fillMaxWidth(),
		onValueChange = onValueChange,
		label = "Email",
		placeholder = "Enter Your Email",
		leadingIcon = {
			Icon(
				painter = painterResource(id = R.drawable.email),
				contentDescription = null,
			)
		}
	)
}