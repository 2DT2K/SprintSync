package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.Purple40

@Preview
@Composable
fun SignUpButtonGroup() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(
            type = "filled",
            text = "Sign Up",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24)
        )

        PromptRow(
            "Already have an account?",
            "Log In",
            Purple40,
            onClick = {}
        )
    }
}