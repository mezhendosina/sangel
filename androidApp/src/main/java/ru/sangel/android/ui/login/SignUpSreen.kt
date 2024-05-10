package ru.sangel.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.android.R
import ru.sangel.android.ui.components.LoginButton
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.components.login.signUp.SignUpComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(component: SignUpComponent) {
    val model by component.model.subscribeAsState()
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
            LoginButton(onClick = component::singUp) {
                Text(stringResource(R.string.next))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxWidth()
                    .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
            item {
                SignInTextField(
                    title = stringResource(R.string.name),
                    value = model.name,
                    onValueChange = component::changeName,
                    placeholder = stringResource(R.string.your_name),
                )
            }
            item {
                SignInTextField(
                    title = stringResource(R.string.phone_number),
                    value = model.phone,
                    onValueChange = component::changePhone,
                    placeholder = stringResource(R.string._7_952_812_751_82),
                )
            }
            item {
                SignInTextField(
                    title = stringResource(R.string.e_mail),
                    value = model.email,
                    onValueChange = component::changeMail,
                    placeholder = stringResource(R.string.your_mail),
                )
            }
        }
    }
}

@Composable
private fun SignInTextField(
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

@Preview
@Composable
private fun PreviewSignUpScreen() {
    SangelTheme {
        SignUpScreen(
            object : SignUpComponent {
                override val model: Value<SignUpComponent.Model>
                    get() = MutableValue(SignUpComponent.Model("", "", ""))

                override fun changeName(name: String) {
                    TODO("Not yet implemented")
                }

                override fun changePhone(phone: String) {
                    TODO("Not yet implemented")
                }

                override fun changeMail(mail: String) {
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
