package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.theme.spacing



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Problem(title: String, incompleteProblems: List<TaskResDto>,statusList:List<String>?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.default)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.large,
                Alignment.Start
            ),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default,
                    Alignment.Top
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Key", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                incompleteProblems.forEach {
                    Text(
                        text = "SCRUMMER",
                        modifier = Modifier.height(MaterialTheme.spacing.mediumLarge),
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7B7B7B),
                        )
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.outlineVariant)

            ) {
                Divider()
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val pagerState = rememberPagerState(pageCount = {
                    2
                })
                HorizontalPager(
                    state = pagerState,
                ) {
                    if (it == 0) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    MaterialTheme.spacing.default,
                                    Alignment.Top
                                ),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = "Summary",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                incompleteProblems.forEach { it2 ->
                                    Text(
                                        text = it2.name,
                                        Modifier
                                            .height(MaterialTheme.spacing.mediumLarge)
                                            .width(160.dp),
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            lineHeight = 20.sp,
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF7B7B7B),
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    MaterialTheme.spacing.default,
                                    Alignment.Top
                                ),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = "Issue type",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                incompleteProblems.forEach { it2 ->
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(
                                            0.dp,
                                            Alignment.CenterHorizontally
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        when ("Task") {
                                            "Task" -> {
                                                Image(
                                                    painter = painterResource(
                                                        id = R.drawable.check_box
                                                    ),
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.None,
                                                    modifier = Modifier
                                                        .width(MaterialTheme.spacing.mediumLarge)
                                                        .height(MaterialTheme.spacing.mediumLarge)
                                                )
                                                Text(
                                                    text = "Task",
                                                    style = MaterialTheme.typography.labelSmall,

                                                    )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                    if (it == 1) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                MaterialTheme.spacing.medium,
                                Alignment.End
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    MaterialTheme.spacing.default,
                                    Alignment.Top
                                ),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = "Status",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                incompleteProblems.forEach { it3 ->
                                    var color = 0L
                                    when (statusList?.get(it3.statusIndex)) {
                                        "In Progress" -> color = 0xFFF7C84F
                                        "Review" -> color = 0xFF4FF7E3
                                        "Todo" -> color = 0xFF4FF774
                                    }
                                    statusList?.get(it3.statusIndex)
                                        ?.let { it1 -> TaskProcess(title = it1, color = color) }
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    MaterialTheme.spacing.default,
                                    Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "Assignee",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                incompleteProblems.forEach { it4 ->
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(
                                            (-14).dp,
                                            Alignment.End
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        it4.assignees.forEach {

                                            Image(
                                                painter = painterResource(
                                                    id = R.drawable.nice_avatar
                                                ),
                                                modifier = Modifier.size(MaterialTheme.spacing.mediumLarge),
                                                contentDescription = ""
                                            )
                                        }
                                    }

                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    MaterialTheme.spacing.default,
                                    Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Story points",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                incompleteProblems.forEach { item ->
                                    TaskPoint(
                                        point = item.point, Modifier
                                            .background(
                                                color = Color(0xFFDCCFE3),
                                                shape = RoundedCornerShape(size = 10.dp)
                                            )
                                            .height(MaterialTheme.spacing.mediumLarge)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun IncompleteProblemPreview() {
//    Problem(
//        title = "Incomplete problem",
//        incompleProblems = listOf(fakeData, fakeData, fakeData, fakeData)
//    )
//}