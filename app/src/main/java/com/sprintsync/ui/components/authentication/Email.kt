package com.sprintsync.ui.components.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sprintsync.R
import com.sprintsync.ui.components.CustomTextField

@Composable
fun Email(){
    CustomTextField(
        label = "Email",
        placeholder = "Please Enter Your Email",
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    )
}