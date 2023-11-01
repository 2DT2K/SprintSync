package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R
import com.sprintsync.ui.components.CustomTextField

@Preview
@Composable
fun Password(type: String = "normal") {
    var placeholder = "Enter Your Password"
    var leadingIcon = R.drawable.key

    if (type == "confirm") {
        placeholder = "confirm Your Password"
        leadingIcon = R.drawable.lock
    }
    CustomTextField(
        type = "hidden",
        label = "Password",
        placeholder = placeholder,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.visibility),
                contentDescription = null,
            )
        }
    )
}