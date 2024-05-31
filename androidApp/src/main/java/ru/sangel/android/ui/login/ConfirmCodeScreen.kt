package ru.sangel.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import ru.sangel.android.R
import ru.sangel.android.ui.components.CodeField
import ru.sangel.android.ui.components.LoginButton
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.components.login.checkCode.ConfirmCodeComponent

@OptIn(ExperimentalTextApi::class)
@Composable
fun ConfirmCodeScreen(component: ConfirmCodeComponent) {
    val model by component.model.subscribeAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(id = R.drawable.ic_login_background),
                null,
                modifier = Modifier.fillMaxWidth(),
            )
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
                    stringResource(R.string.enter_code),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(8.dp))
                CodeField(value = model.code, length = 6, onValueChange = component::onCodeChanges)
                Text(
                    buildAnnotatedString {
                        append("Выслать новый код можно через ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            withAnnotation(UrlAnnotation("sangel://signIn")) {
                                append(model.timer.toTimer())
                            }
                        }
                    },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.size(64.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LoginButton(onClick = component::toMain) {
                        Text(text = "ОТПРАВИТЬ КОД")
                    }
                }
            }
        }
    }
}

private fun Int.toTimer(): String = "$this секунд"

@Preview
@Composable
private fun SignInPreview() {
    SangelTheme {
        ConfirmCodeScreen(
            object : ConfirmCodeComponent {
                override val model: Value<ConfirmCodeComponent.Model>
                    get() = TODO("Not yet implemented")

                override fun onCodeChanges(code: String) {
                    TODO("Not yet implemented")
                }

                override fun toMain() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
