package com.sprintsync.ui.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.theme.Grey40

@Composable
fun PromptRow(normalText: String, highlightedText: String, highlightColor: Color, onClick: () -> Unit, modifier: Modifier = Modifier.height(40.dp)) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CustomText(
            text = normalText,
            color = Grey40
        )

        Spacer(modifier = Modifier.width(5.dp))

        CustomText(
            modifier = Modifier.clickable(interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    radius = 250.dp
                ),
                onClick = onClick
            ),
            text = highlightedText,
            color = highlightColor,
            fontWeight = FontWeight.Bold
        )
    }
}