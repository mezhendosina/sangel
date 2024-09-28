package ru.sangel.zaya.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.R
import ru.sangel.zaya.ui.components.ErrorState
import ru.sangel.zaya.ui.components.LoginButton
import ru.sangel.zaya.ui.theme.SangelTheme
import ru.sangel.zaya.presentation.components.login.signUp.SignUpComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(component: SignUpComponent) {
    val model by component.model.subscribeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    ErrorState(model.state, snackbarHostState)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                title = { Text(text = stringResource(R.string.sign_up)) },
                navigationIcon = {
                    IconButton(onClick = component::onBack) {
                        Image(
                            Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            stringResource(id = R.string.back),
                        )
                    }
                },
                actions = {
                    Image(
                        painterResource(id = R.drawable.ic_logo),
                        null,
                        modifier =
                            Modifier
                                .padding(end = 16.dp)
                                .size(24.dp),
                    )
                },
            )
        },
        floatingActionButton = {
            LoginButton(onClick = component::singUp, modifier = Modifier.navigationBarsPadding()) {
                Text(stringResource(R.string.next))
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
            item {
                SignUpTextField(
                    title = stringResource(R.string.name),
                    value = model.firstName,
                    onValueChange = component::changeName,
                    placeholder = stringResource(R.string.your_name),
                )
            }
            item {
                SignUpTextField(
                    title = stringResource(R.string.phone_number),
                    value = model.phoneNumber,
                    onValueChange = component::changePhone,
                    placeholder = stringResource(R.string._7_952_812_751_82),
                )
            }
            item {
                SignUpTextField(
                    title = stringResource(R.string.e_mail),
                    value = model.email,
                    onValueChange = component::changeMail,
                    placeholder = stringResource(R.string.your_mail),
                )
            }
            item {
                SignUpTextField(
                    title = stringResource(R.string.password),
                    value = model.password,
                    onValueChange = component::changePassword,
                    placeholder = stringResource(R.string.password),
                )
            }
        }
    }
}

@Composable
private fun SignUpTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    Text(
        title,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
    )
}

@Preview(device = "id:Nexus One", showSystemUi = true)
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:pixel_fold")
@Composable
private fun PreviewSignUpScreen() {
    SangelTheme {
        SignUpScreen(
            object : SignUpComponent {
                override val model: Value<SignUpComponent.Model>
                    get() = MutableValue(SignUpComponent.Model.init())

                override fun changeName(name: String) {
                    TODO("Not yet implemented")
                }

                override fun changePhone(phone: String) {
                    TODO("Not yet implemented")
                }

                override fun changeMail(mail: String) {
                    TODO("Not yet implemented")
                }

                override fun changePassword(password: String) {
                    TODO("Not yet implemented")
                }

                override fun singUp() {
                    TODO("Not yet implemented")
                }

                override fun onBack() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
