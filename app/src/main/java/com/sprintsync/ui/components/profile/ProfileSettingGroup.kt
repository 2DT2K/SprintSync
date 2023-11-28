package com.sprintsync.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.Grey120
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.profile.Setting


@Composable
fun ProfileSettingGroup(title: String, settings: List<Setting>) {
	Surface {
		Column(
			modifier = Modifier.fillMaxWidth(),
			verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
		) {
			Text(
				text = title,
				style = MaterialTheme.typography.titleMedium,
				color = MaterialTheme.colorScheme.onSurface
			)

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						color = MaterialTheme.colorScheme.secondaryContainer,
						shape = RoundedCornerShape(size = 16.dp)
					)
					.padding(MaterialTheme.spacing.smallMedium),
				verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
			) {
				settings.forEach { setting ->
					Row(
						modifier = Modifier.clickable { setting.onClick() },
						horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
						verticalAlignment = Alignment.CenterVertically
					) {
						Icon(
							modifier = Modifier.size(32.dp),
							painter = painterResource(id = setting.icon),
							contentDescription = setting.settingName,
							tint = MaterialTheme.colorScheme.onSecondaryContainer
						)
						Text(
							text = setting.settingName,
							style = MaterialTheme.typography.bodyLarge,
						)
					}
				}
			}
		}
	}
}
