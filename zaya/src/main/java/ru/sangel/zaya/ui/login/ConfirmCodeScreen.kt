package ru.sangel.zaya.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.R
import ru.sangel.zaya.presentation.components.login.checkCode.ConfirmCodeComponent
import ru.sangel.zaya.presentation.entities.States
import ru.sangel.zaya.ui.components.CodeField
import ru.sangel.zaya.ui.components.ErrorState
import ru.sangel.zaya.ui.components.LoginButton
import ru.sangel.common.ui.theme.SangelTheme

@Composable
fun ConfirmCodeScreen(component: ConfirmCodeComponent) {
    val model by component.model.subscribeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    ErrorState(model.state, snackbarHostState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 32.dp)
        ) {
            Text(
                stringResource(R.string.enter_code),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(8.dp))
            CodeField(value = model.code, length = 6, onValueChange = component::onCodeChanges)
//                Text(
//                    buildAnnotatedString {
//                        append("Выслать новый код можно через ")
//                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
//                            withAnnotation(UrlAnnotation("sangel://signIn")) {
//                                append(model.timer.toTimer())
//                            }
//                        }
//                    },
//                    style = MaterialTheme.typography.labelLarge,
//                    fontWeight = FontWeight.Medium,
//                    color = MaterialTheme.colorScheme.onBackground,
//                )
            Spacer(modifier = Modifier.size(64.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoginButton(onClick = component::toMain) {
                    Text(text = "ОТПРАВИТЬ КОД", fontSize = 12.sp)
                }
            }
        }
    }
}

private fun Int.toTimer(): String = "$this секунд"

@Preview(device = "id:Nexus One")
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:pixel_fold")
@Composable
private fun SignInPreview() {
    SangelTheme {
        ConfirmCodeScreen(
            object : ConfirmCodeComponent {
                override val model: Value<ConfirmCodeComponent.Model>
                    get() =
                        MutableValue(
                            ConfirmCodeComponent.Model("", 0, States.Loaded),
                        )

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
