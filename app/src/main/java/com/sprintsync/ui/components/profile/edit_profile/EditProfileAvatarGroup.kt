package com.sprintsync.ui.components.profile.edit_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomModalBottomSheet
import com.sprintsync.ui.theme.Grey120

@Composable
fun EditProfileAvatarGroup() {
	var showBottomSheet by remember { mutableStateOf(false) }

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		contentAlignment = Alignment.Center
	) {
		Box(
			modifier = Modifier
				.wrapContentHeight()
		) {
			Image(
				modifier = Modifier
					.clickable { showBottomSheet = true }
					.width(200.dp)
					.height(200.dp),
				painter = painterResource(id = R.drawable.nice_avatar),
				contentDescription = "edit profile avatar"
			)

			Icon(
				modifier = Modifier
					.align(Alignment.BottomEnd)
					.offset(
						(-14).dp,
						(-14).dp
					)
					.padding(0.dp)
					.size(36.dp)
					.background(
						color = Grey120,
						shape = RoundedCornerShape(100)
					)
					.padding(4.dp),
				painter = painterResource(id = R.drawable.camera),
				contentDescription = "camera",
				tint = Color(0xCF242760)
			)
		}
	}

	CustomModalBottomSheet(
		isSheetShown = showBottomSheet,
		changeVisibility = { showBottomSheet = it }) {
		Column(
			modifier = Modifier.padding(start = 10.dp),
		) {

			Row(
				modifier = Modifier
					.clickable { }
					.padding(
						start = 8.dp,
						top = 16.dp,
						bottom = 16.dp
					)
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					modifier = Modifier.size(40.dp),
					painter = painterResource(id = R.drawable.account_circle),
					contentDescription = "See Profile Picture",
				)

				Text(text = "See Profile Picture")
			}

			Row(
				modifier = Modifier
					.clickable { }
					.padding(
						start = 8.dp,
						top = 16.dp,
						bottom = 16.dp
					)
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					modifier = Modifier.size(40.dp),
					painter = painterResource(id = R.drawable.image),
					contentDescription = "Choose profile picture",
				)

				Text(text = "Choose profile picture")
			}
		}
	}
}