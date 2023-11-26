package com.sprintsync.ui.components.boardview.boardview_category_item


import android.graphics.Bitmap
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
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.theme.spacing


class IBoardviewCategoryItem(
    var taskName: String,
    var taskTag: List<String>,
    var taskNavigatation: String,
    var taskImage: Bitmap?,
    var taskPoint: Number,
    var taskAssignList: List<Bitmap>?,
)

@Composable
fun BoardViewCategoryItem(boardviewItemDetails: IBoardviewCategoryItem) {
	Column(
		modifier = Modifier
			.background(
				color = MaterialTheme.colorScheme.surface,
				shape = RoundedCornerShape(8.dp)
			)
			.fillMaxWidth()
	) {
		boardviewItemDetails.taskImage?.let {
			Image(
				bitmap = it.asImageBitmap(),
				contentDescription = "",
				modifier = Modifier.fillMaxWidth()
			)
		}
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(MaterialTheme.spacing.default),
			verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default, Alignment.Top),
			horizontalAlignment = Alignment.Start
		) {
			Text(
				text = boardviewItemDetails.taskName,
				style = MaterialTheme.typography.labelMedium,
				color = MaterialTheme.colorScheme.onSurface,
				letterSpacing = 0.5.sp
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
				verticalAlignment = Alignment.Top,
				modifier = Modifier.height(28.dp)
			) {
				boardviewItemDetails.taskTag.forEach {
//					TaskTag(tagName = it)
					SuggestionChip(
						onClick = { /*TODO*/ },
						label = { Text(
							it,
							style = MaterialTheme.typography.labelMedium,
						) },
						border = null,
						shape = RoundedCornerShape(size = 4.dp),
						colors = SuggestionChipDefaults.suggestionChipColors(
							containerColor = MaterialTheme.colorScheme.secondaryContainer,
							labelColor = MaterialTheme.colorScheme.onSecondary,
						),
					)
				}
			}
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.fillMaxWidth()
			) {
				Row(
					horizontalArrangement = Arrangement.spacedBy(
						MaterialTheme.spacing.small,
						Alignment.CenterHorizontally
					),
					verticalAlignment = Alignment.CenterVertically
				) {
					Image(
						painter = painterResource(id = R.drawable.check_box),
						contentDescription = "",
						modifier = Modifier
							.width(24.dp)
							.height(24.dp)
					)
					Text(
						text = boardviewItemDetails.taskNavigatation.toUpperCase(Locale.current),
						style = MaterialTheme.typography.labelMedium,
						color = MaterialTheme.colorScheme.primary,
						textAlign = TextAlign.Center,
						letterSpacing = 0.5.sp,
					)
				}
				Row(
					horizontalArrangement = Arrangement.spacedBy(
						MaterialTheme.spacing.small,
						Alignment.CenterHorizontally
					),
					verticalAlignment = Alignment.CenterVertically
				) {
					TaskPoint(
						boardviewItemDetails.taskPoint,
						Modifier
							.height(24.dp)
							.background(
								color = MaterialTheme.colorScheme.secondaryContainer,
								shape = RoundedCornerShape(size = 8.dp)
							)
							.padding(MaterialTheme.spacing.small)
					)
					Box {
//                        if (boardviewItemDetails?.taskAssignList != null) {
//                            for (image in boardviewItemDetails.taskAssignList!!) {
//                                Image(
//                                    bitmap = image,
//                                    contentDescription = "",
//                                    modifier = Modifier
//                                        .align(Alignment.Center)
//                                        .size(32.dp)
//                                        .padding(10.dp)
//                                )
//                            }
//                        }
						Image(
							painter = painterResource(id = R.drawable.profile),
							contentDescription = ""
						)
//
					}
				}

            }
        }
    }
}

var fakeData: IBoardviewCategoryItem = IBoardviewCategoryItem(
    "Code Homepage",
    listOf("Homepage", "FE"),
    "Scrummer123",
    null,
    90,
    null,
)

@Preview
@Composable
fun BoardViewCategoryItemPreview() {
    BoardViewCategoryItem(fakeData)
}