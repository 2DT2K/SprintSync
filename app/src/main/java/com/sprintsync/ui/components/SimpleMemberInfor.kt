package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing

@Composable
fun SimpleMemberInfor(name: String) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(
			MaterialTheme.spacing.small,
			Alignment.CenterHorizontally
		),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Image(
			painter = painterResource(id = R.drawable.avataricon),
			contentDescription = "",
			modifier = Modifier.width(MaterialTheme.spacing.large)
		)
		Text(
			text = name,
			style = MaterialTheme.typography.bodyLarge,
			color = MaterialTheme.colorScheme.onSecondaryContainer
		)
	}
}
@Preview(showBackground = true)
@Composable
fun SimpleMemberInforPreview(){
	SimpleMemberInfor(name = "Vo Tin Du")
}