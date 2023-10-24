package com.sprintsync.ui.components.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.theme.Purple40


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
            surfaceModifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth(),
                surfaceModifier = Modifier
                    .weight(1f),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            )

            CustomButton(
                type = "outlined",
                text = "Phone",
                modifier = Modifier.fillMaxWidth(),
                surfaceModifier = Modifier
                    .weight(1f),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            )
        }
    }
}
