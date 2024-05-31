package ru.sangel.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.android.R
import ru.sangel.android.ui.theme.SangelTheme

@Composable
fun AddNewDeviceCard(onClick: () -> Unit) {
    AdditionalDeviceCard(
        onClick = onClick,
        borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        containerColor = Color.Transparent,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(12.dp),
        ) {
            Image(
                painterResource(id = R.drawable.ic_plus),
                null,
                modifier = Modifier.fillMaxWidth().size(12.dp),
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Подключить\nновое\nустройство",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddNewDeviceCard() {
    SangelTheme {
        AddNewDeviceCard {
//
        }
    }
}
