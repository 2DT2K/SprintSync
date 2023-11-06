package com.sprintsync.ui.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.auth.EmailField
import com.sprintsync.ui.components.auth.PasswordField
import com.sprintsync.ui.components.auth.PromptRow
import com.sprintsync.ui.components.auth.SignInButtonGroup
import com.sprintsync.ui.components.auth.Title
import com.sprintsync.ui.theme.Red80
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    signInWithPassword: (String, String) -> Unit = { _, _ -> },
    signInWithGoogle: () -> Unit = {},
    resetPassword: () -> Unit = {},
    signUp: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {
        // TODO: remove padding when we have main scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize()
                    .weight(0.7f),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = modifier.requiredSize(240.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                Title(
                    title = "Hi, Welcome Back! ",
                    subtitle = "Hello again, we missed you <3"
                )
            }

            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                EmailField(
                    onValueChange = { email = it },
                    errorText = "Email is incorrect"
//                    Có nhiều loại lỗi nên sẽ phải tùy trường hợp để đặt errorText là gì
//                    Có thể gọi isError để hiển thị lỗi và tắt hiểu thị lỗi khi người dùng bắt
//                    đầu nhập lại
                )

                PasswordField(
                    onValueChange = { password = it },
                    errorText = "Password is incorrect"
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    PromptRow(
                        modifier = Modifier.height(20.dp),
                        normalText = "Forgot Password?",
                        highlightedText = "Reset",
                        highlightColor = Red80,
                        onClick = resetPassword
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInButtonGroup(
                    signInWithPassword = { signInWithPassword(email, password) },
                    signInWithGoogle = signInWithGoogle,
                    signUp = signUp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
    SprintSyncTheme {
        SignInView()
    }
}
