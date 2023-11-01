package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sprintsync.R
import com.sprintsync.ui.components.ExpandTextField

@Composable
fun Email(){
    ExpandTextField(
        label = "Email",
        placeholder = "Enter Your Email",
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.email),
                contentDescription = null,
            )
        }
    )
}