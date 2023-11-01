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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.views.Task
import com.sprintsync.ui.views.fakeData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Problem(title: String, incompleProblems: List<Task>) {
	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp)
	) {
		Text(
			text = title, style = TextStyle(
				fontSize = 18.sp,
				fontWeight = FontWeight(600),
				color = Color(0xFF243465),
				letterSpacing = 0.28.sp,
			)
		)
		Row(
			horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
			verticalAlignment = Alignment.Top,
			modifier = Modifier.padding(start = 4.dp, end = 4.dp)
		) {
			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
				horizontalAlignment = Alignment.Start,
			) {
				Text(
					text = "Key", style = TextStyle(
						fontSize = 12.sp,
						lineHeight = 14.4.sp,
						fontWeight = FontWeight(500),
						color = Color(0xFF7B7B7B),
					)
				)
				incompleProblems.forEach {
					Text(
						text = it.taskNavigation,
						Modifier.height(22.dp), style = TextStyle(
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
								verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
								horizontalAlignment = Alignment.Start,
							) {
								Text(
									text = "Summary",
									style = TextStyle(
										fontSize = 12.sp,
										lineHeight = 14.4.sp,
										fontWeight = FontWeight(500),
										color = Color(0xFF7B7B7B),
									)
								)
								incompleProblems.forEach { it2 ->
									Text(
										text = it2.name,
										Modifier
											.height(22.dp)
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
								verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
								horizontalAlignment = Alignment.Start,
							) {
								Text(
									text = "Issue type",
									style = TextStyle(
										fontSize = 12.sp,
										lineHeight = 14.4.sp,
										fontWeight = FontWeight(500),
										color = Color(0xFF7B7B7B),
									)
								)
								incompleProblems.forEach { it2 ->
									Row(
										horizontalArrangement = Arrangement.spacedBy(
											0.dp,
											Alignment.CenterHorizontally
										),
										verticalAlignment = Alignment.CenterVertically,
									) {
										when (it2.issueType) {
											"Task" -> {
												Image(
													painter = painterResource(
														id = R.drawable.check_box
													),
													contentDescription = "image description",
													contentScale = ContentScale.None,
													modifier = Modifier
														.padding(1.dp)
														.width(19.dp)
														.height(19.dp)
												)
												Text(
													text = "Task",
													style = TextStyle(
														fontSize = 12.sp,
														lineHeight = 20.sp,
														fontWeight = FontWeight(500),
														color = Color(0xD921005D),
														textAlign = TextAlign.Center,
														letterSpacing = 0.1.sp,
													)
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
							horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
							verticalAlignment = Alignment.Top
						) {
							Column(
								verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
								horizontalAlignment = Alignment.Start,
							) {
								Text(
									text = "Status",
									style = TextStyle(
										fontSize = 12.sp,
										lineHeight = 14.4.sp,
										fontWeight = FontWeight(500),
										color = Color(0xFF7B7B7B),
									)
								)
								incompleProblems.forEach { it3 ->
									var color = 0L
									when (it3.taskState) {
										"In Progress" -> color = 0xFFF7C84F
										"Review"      -> color = 0xFF4FF7E3
										"Todo"        -> color = 0xFF4FF774
									}
									TaskProcess(title = it3.taskState, color = color)


								}
							}
							Column(
								verticalArrangement = Arrangement.spacedBy(
									8.dp,
									Alignment.CenterVertically
								),
								horizontalAlignment = Alignment.CenterHorizontally,
							) {
								Text(
									text = "Assignee",
									style = TextStyle(
										fontSize = 12.sp,
										lineHeight = 14.4.sp,
										fontWeight = FontWeight(500),
										color = Color(0xFF7B7B7B),
									)
								)
								incompleProblems.forEach { it4 ->
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
												modifier = Modifier.size(22.dp),
												contentDescription = ""
											)
										}
									}

								}
							}
							Column(
								verticalArrangement = Arrangement.spacedBy(
									8.dp,
									Alignment.CenterVertically
								),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(
									text = "Story points",
									style = TextStyle(
										fontSize = 12.sp,
										lineHeight = 14.4.sp,
										fontWeight = FontWeight(500),
										color = Color(0xFF7B7B7B),
									)
								)
								incompleProblems.forEach { item ->
									TaskPoint(
										point = item.point, Modifier
											.background(
												color = Color(0xFFDCCFE3),
												shape = RoundedCornerShape(size = 10.dp)
											)
											.height(22.dp)
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

@Preview(showBackground = true)
@Composable
fun IncompleteProblemPreview() {
	Problem(
		title = "Incomplete problem",
		incompleProblems = listOf(fakeData, fakeData, fakeData, fakeData)
	)
}