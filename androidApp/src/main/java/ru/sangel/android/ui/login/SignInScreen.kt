package ru.sangel.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.android.R
import ru.sangel.android.ui.components.LoginButton
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.components.login.signIn.SignInComponent
import ru.sangel.presentation.entities.States

@OptIn(ExperimentalTextApi::class)
@Composable
fun SignInScreen(component: SignInComponent) {
    val model by component.model.subscribeAsState()
    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_sign_in))

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                LottieAnimation(
                    composition = animation,
                    speed = 0.1f,
                    reverseOnRepeat = true,
                    modifier =
                        Modifier
                            .fillMaxHeight(0.5f)
                            .clip(
                                RoundedCornerShape(bottomStart = 52.dp, bottomEnd = 52.dp),
                            ),
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.FillBounds,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    null,
                    modifier = Modifier.size(40.dp),
                )
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 32.dp,
                            vertical = 28.dp,
                        ),
            ) {
                Text(
                    stringResource(R.string.login),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(8.dp))
                OutlinedTextField(
                    value = model.email,
                    onValueChange = component::onEmailChange,
                    shape = RoundedCornerShape(32.dp),
                    singleLine = true,
                    placeholder = { Text("mail@example.ru") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.size(64.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LoginButton(onClick = component::signIn) {
                        Text(text = stringResource(R.string.send_code))
                    }
                }
            }
        }
    }
}

@Preview
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
                                States.LOADED,
                            ),
                        )

                override fun onEmailChange(email: String) {
                    TODO("Not yet implemented")
                }

                override fun signIn() {
                    TODO("Not yet implemented")
                }

                override fun toCheckCode() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}