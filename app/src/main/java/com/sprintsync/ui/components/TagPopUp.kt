package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.sprintsync.R
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagPopUp(
    onDismissRequest: () -> Unit,
    updateState: (TaskDto) -> Unit,
    taskDetails: TaskDto
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            usePlatformDefaultWidth = false // disable the default size so that we can customize it
        )
    ) {
        val currentTaskDetails = taskDetails
        var textState by remember {
            mutableStateOf("")
        }
        var currentTagList by remember {
            mutableStateOf(currentTaskDetails.labels)
        }
        var finalTagList by remember {
            mutableStateOf(currentTaskDetails.labels)
        }
        Box(modifier = Modifier
            .background(Color.Black.copy(alpha = 0.5f))
            .fillMaxSize()
            .clickable { onDismissRequest() }
            .padding(top = 120.dp)
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .width(300.dp)
                    .padding(MaterialTheme.spacing.medium)
                    .align(alignment = Alignment.TopCenter)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {

                    },
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium,
                    Alignment.CenterVertically
                ),
            ) {
                Text(
                    text = "Labels",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.default,
                        Alignment.CenterVertically
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.default,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 170.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .pointerInput(Unit) {
                                // Consume the pointer events inside the Row
                                // to prevent the Box's click handler from being triggered
//                                this.

                            }
                    ) {
                        currentTagList?.forEach {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                var checkState by remember {
                                    mutableStateOf(true)
                                }
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
                                IconButton(onClick = {
                                    finalTagList = if (checkState) {
                                        finalTagList?.minus(it)
                                    } else {
                                        finalTagList?.plus(it)
                                    }
                                    checkState = !checkState

                                }) {
                                    if (checkState) {
                                        Image(
                                            painter = painterResource(id = R.drawable.check_popup),
                                            contentDescription = "",
                                            modifier = Modifier.size(25.dp)
                                        )
                                    } else {
                                        Image(
                                            painter = painterResource(id = R.drawable.add_label),
                                            contentDescription = "", modifier = Modifier.size(30.dp)
                                        )
                                    }
                                }

                            }
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = textState,
                            onValueChange = {
                                textState = it
                            },
                            placeholder = {
                                Text(text = "Input new label")
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .height(52.dp)
                        )
                        IconButton(onClick = {
                            if (textState.isNotEmpty() && !currentTagList?.any {
                                    it.equals(textState, ignoreCase = true)
                                }!!) {
                                currentTagList = currentTagList?.plus(textState)
                                finalTagList = currentTagList
                                textState = ""
                            }

                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.add_label),
                                contentDescription = "", modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Button(
                        onClick = {
                            currentTaskDetails.labels = finalTagList
                            updateState(currentTaskDetails)
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .border(
                                0.dp,
                                Color.Transparent,
                                RoundedCornerShape(3.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Done",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

            }
        }
    }

}


//@Preview(showBackground = true)
//@Composable
//fun TagPopUpPreview() {
//    TagPopUp({}, listOf("HomePage", "FE"))
//}