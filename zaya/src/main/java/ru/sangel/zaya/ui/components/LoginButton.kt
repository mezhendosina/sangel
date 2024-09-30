package ru.sangel.zaya.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.ui.theme.SangelTheme

@Composable
fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope) -> Unit,
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 78.dp, vertical = 16.dp),
        content = content,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}

@Preview
@Composable
private fun PreviewLoginButton() {
    SangelTheme {
        LoginButton(onClick = { /*TODO*/ }) {
            Text("123")
        }
    }
}
