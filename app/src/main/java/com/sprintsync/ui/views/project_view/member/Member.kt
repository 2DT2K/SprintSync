package com.sprintsync.ui.views.project_view.member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.SprintSyncTheme
import java.util.Date
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.SearchBar

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
    val uiUxDesigner: Color = Color.Yellow,
    val beDeveloper: Color = Color.Blue,
    val feDeveloper: Color = Color.Green
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
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SearchBar(placeHolder = "Search a member", onValueChange = { searchTerm = it })
            memberList.forEach() { member ->
                if (member.name.contains(searchTerm)) MemberCard(
                    memberName = member.name,
                    teamName = member.teamName,
                    role = member.role,
                )
            }
        }
    }
}

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
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "avatar"
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start,
                ) {
                    CustomText(text = memberName)
                    CustomText(
                        text = teamName,
                        fontWeight = FontWeight(500),
                        color = Color(0xD95E4E79),
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(roleColor),
                    contentAlignment = Alignment.Center
                ) {
                    CustomText(
                        modifier = Modifier.padding(8.dp),
                        text = role.replace("_", " "),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
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