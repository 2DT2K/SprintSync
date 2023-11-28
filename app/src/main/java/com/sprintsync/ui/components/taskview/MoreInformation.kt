package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.SimpleMemberInfor
import com.sprintsync.ui.components.TagPopUp
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformation(
    updateState: (TaskDto) -> Unit,
    taskDetails: TaskResDto,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.mediumLarge,
                Alignment.Start
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = "Issue Type",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_box),
                        contentDescription = "",
                        modifier = Modifier
                            .width(MaterialTheme.spacing.mediumLarge)
                            .height(MaterialTheme.spacing.mediumLarge)
                    )
                    Text(
                        text = "Task", style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(53.dp)
                    .padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    )
                    .width(1.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = "Point", style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = taskDetails.point.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.default,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = "Assignee",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default,
                    Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                taskDetails.assignees.forEach {
                    SimpleMemberInfor(name = it.name)
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.largeDefault,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = "Labels",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.largeDefault,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(MaterialTheme.spacing.extraLarge)
            ) {
                if (isDialogVisible) {
                    taskDetails.labels?.let {
                        TagPopUp(
                            { isDialogVisible = false },
                            taskDetails = taskDetails.toDto(),
                            updateState = updateState
                        )
                    }
                    taskDetails.labels?.forEach {
                        SuggestionChip(
                            onClick = { /*TODO*/ },
                            label = { Text(it) },
                            border = null,
                            shape = RoundedCornerShape(size = MaterialTheme.spacing.small),
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                        )
                    }
                    Button(
                        onClick = { isDialogVisible = true },
                        contentPadding = PaddingValues(
                            MaterialTheme.spacing.default
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        border = BorderStroke(1.dp, Color(0xFF79747E)),
                        shape = RoundedCornerShape(size = 4.dp),
                    ) {
                        Text(
                            text = "Add ",
                            style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 12.2.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF49454F),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.1.sp,
                            )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.normal_add),
                            contentDescription = "",
                            modifier = Modifier.height(18.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Reporter",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SimpleMemberInfor(name = taskDetails.assignor.name)
                }
            }
        }
    }
}

//    @Preview(showBackground = true)
//    @Composable
//    fun MoreInformationPreview() {
//
//    }