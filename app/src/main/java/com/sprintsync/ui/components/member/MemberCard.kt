package com.sprintsync.ui.components.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing
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
        "FE_developer" -> roleColor = RoleColor().feDeveloper
        "BE_developer" -> roleColor = RoleColor().beDeveloper
        "UI/UX_developer" -> roleColor = RoleColor().uiUxDesigner
    }

    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    painter = painterResource(id = R.drawable.nice_avatar),
                    contentDescription = "avatar"
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = memberName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = teamName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        color = roleColor,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(
                        vertical = MaterialTheme.spacing.default,
                        horizontal = MaterialTheme.spacing.medium
                    )
            ) {
                Text(
                    text = role.replace("_", " "),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }
}