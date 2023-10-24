package com.sprintsync.ui.components.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R
import com.sprintsync.ui.components.CustomTextField

@Preview
@Composable
fun Password(){
    CustomTextField(
        type = "hidden",
        label = "Password",
        placeholder = "Please Enter Your Password",
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.key),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.visibility),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    )
}