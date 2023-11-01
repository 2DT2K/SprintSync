package com.sprintsync.ui.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.theme.Purple40


@Preview
@Composable
fun SignInButtonGroup() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            type = "filled",
            text = "Login",
            fontSize = 18.sp,
            surfaceModifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24)
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .weight(1f)
                    .background(Purple40)
            ) {}

            CustomText(
                text = "Or With",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Purple40
                ),
            )

            Box(
                modifier = Modifier
                    .height(2.dp)
                    .weight(1f)
                    .background(Purple40)
            ) {}
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CustomButton(
                type = "outlined",
                text = "Google",
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                surfaceModifier = Modifier
                    .height(48.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                    )
                },
                shape = RoundedCornerShape(24)
            )
        }

        PromptRow(
            "Don't have an account?",
            "Sign Up",
            Purple40,
            onClick = {})
    }
}
