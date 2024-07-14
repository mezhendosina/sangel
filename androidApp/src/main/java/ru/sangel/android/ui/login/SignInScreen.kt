package ru.sangel.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.android.R
import ru.sangel.android.ui.components.ErrorState
import ru.sangel.android.ui.components.LoginButton
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.components.login.signIn.SignInComponent
import ru.sangel.presentation.entities.States

@Composable
fun SignInScreen(component: SignInComponent) {
    val model by component.model.subscribeAsState()
    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_sign_in))
    val snackbarHostState = remember { SnackbarHostState() }

    LazyColumn(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = 32.dp,
                vertical = 28.dp,
            ),
    ) {
        item {
            Text(
                stringResource(R.string.login),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
        item {
            Spacer(modifier = Modifier.size(8.dp))
        }
        item {
            OutlinedTextField(
                value = model.email,
                onValueChange = component::onEmailChange,
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                placeholder = { Text("mail@example.ru") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            TextButton(onClick = component::toSignUp) {
                Text(stringResource(R.string.or_sign_up))
            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoginButton(onClick = component::signIn) {
                    Text(text = stringResource(R.string.send_code), fontSize = 12.sp)
                }
            }
        }
    }
    ErrorState(model.state, snackbarHostState)

}

@Preview(device = "id:Nexus One")
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:pixel_fold")
@Composable
private fun SignInPreview() {
    SangelTheme {
        SignInScreen(
            object : SignInComponent {
                override val model: Value<SignInComponent.Model>
                    get() =
                        MutableValue(
                            SignInComponent.Model(
                                "",
                                States.Loaded,
                            ),
                        )

                override fun onEmailChange(email: String) {
                    TODO("Not yet implemented")
                }

                override fun signIn() {
                    TODO("Not yet implemented")
                }

                override fun toSignUp() {
                    TODO("Not yet implemented")
                }

                override fun toCheckCode() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

@Composable
private fun Preview() {
}
