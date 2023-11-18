package com.sprintsync.ui.components.member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.views.project_view.member.RoleColor


@Composable
fun MemberCard(
	avatar: String = "",
	memberName: String,
	teamName: String,
	role: String,
) {
	var roleColor: Color = Color.Transparent
	when (role) {
		"FE_developer"    -> roleColor = RoleColor().feDeveloper
		"BE_developer"    -> roleColor = RoleColor().beDeveloper
		"UI/UX_developer" -> roleColor = RoleColor().uiUxDesigner
	}

	Surface {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(56.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Row(
				modifier = Modifier.weight(1f),
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					modifier = Modifier
						.width(36.dp)
						.height(36.dp)
						.clip(shape = RoundedCornerShape(1f)),
					painter = painterResource(id = R.drawable.email),
					contentDescription = "avatar"
				)
				Column(
					verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
					horizontalAlignment = Alignment.Start,
				) {
					Text(text = memberName)
					Text(
						text = teamName,
						fontWeight = FontWeight(500),
						color = Grey60,
					)
				}
			}
			Box(
				modifier = Modifier
					.weight(0.8f)
					.padding(8.dp)
					.fillMaxHeight()
					.clip(shape = RoundedCornerShape(8.dp))
					.background(roleColor),
				contentAlignment = Alignment.Center
			) {
				Text(
					modifier = Modifier.padding(8.dp),
					text = role.replace("_", " "),
					textAlign = TextAlign.Center,
					color = Color.White
				)
			}
		}
	}
}