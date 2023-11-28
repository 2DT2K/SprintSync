package com.sprintsync.ui.views.project_view.member

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.member.MemberCard
import com.sprintsync.ui.theme.Cyan80
import com.sprintsync.ui.theme.Green80
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.Yellow80
import com.sprintsync.ui.theme.spacing
import java.util.Date

data class Member(
	val id: Int,
	val name: String,
	val email: String,
	val password: String,
	val dob: Date,
	val role: String,
	val teamName: String
)

data class RoleColor(
	val uiUxDesigner: Color = Yellow80,
	val beDeveloper: Color = Cyan80,
	val feDeveloper: Color = Green80
)

val memberList = listOf(
	Member(1, "khoi", "khoi@gmail.com", "fadfa", Date(2003), "FE_developer", "SprintSync"),
	Member(2, "khoi", "khoi@gmail.com", "fadfa", Date(2003), "BE_developer", "SprintSync"),
	Member(3, "khoi", "khoi@gmail.com", "fadfa", Date(2003), "FE_developer", "SprintSync"),
	Member(4, "khoi", "khoi@gmail.com", "fadfa", Date(2003), "BE_developer", "SprintSync"),
	Member(5, "khoi", "khoi@gmail.com", "fadfa", Date(2003), "UI/UX_developer", "SprintSync"),
)

@Composable
fun Member() {
	var searchTerm by remember {
		mutableStateOf("")
	}
	Surface {
		Column(
			modifier = Modifier
				.verticalScroll(rememberScrollState())
		) {
			SearchBar(placeHolder = "Search a member", onValueChange = { searchTerm = it })
			Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
			memberList.forEach() { member ->
				if (member.name.contains(searchTerm))
					MemberCard(
						memberName = member.name,
						teamName = member.teamName,
						role = member.role,
					)
			}
		}
	}
}

@Preview
@Composable
fun MemberPreview() {
	SprintSyncTheme {
		Member()
	}
}