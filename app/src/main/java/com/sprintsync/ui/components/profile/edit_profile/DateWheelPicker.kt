package com.sprintsync.ui.components.profile.edit_profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.Purple40
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun DateWheelPicker(
    title: String = "Select Your Birth Date",
    confirmation: String = "Confirm Date of Birth",
    onValueChange: (String) -> Unit
) {
    var isOpen by remember { mutableStateOf(false) }
    var birthDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedDate by remember { mutableStateOf(birthDate) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = birthDate.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ),
            onValueChange = {},
            shape = RoundedCornerShape(16),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple40,
                unfocusedBorderColor = Purple40
            ),
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(28.dp),
                    painter = painterResource(
                        id = R.drawable.calendar
                    ),
                    contentDescription = null
                )
            }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable { isOpen = !isOpen },
        )
    }

    if (isOpen) {
        Dialog(onDismissRequest = {
            isOpen = !isOpen
        }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500)
                    )

                    WheelDatePicker(
                        startDate = selectedDate,
                        minDate = LocalDate.of(1000, 10, 20),
                        maxDate = LocalDate.of(2300, 10, 20),
                        yearsRange = IntRange(1800, 2500),
                        size = DpSize(280.dp, 160.dp),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                        )
                    ) { snappedDate ->
                        selectedDate = snappedDate
                    }

                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        text = confirmation,
                        fontSize = 16.sp,
                        onClick = {
                            birthDate = selectedDate
                            onValueChange(birthDate.toString())
                            isOpen = !isOpen
                        },
                        shape = RoundedCornerShape(30),
                    )
                }
            }
        }
    }
}
