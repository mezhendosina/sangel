package ru.sangel.zaya.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.zaya.ui.theme.SangelTheme

@Composable
fun AdditionalDeviceCard(
    modifier: Modifier =
        Modifier,
    borderStroke: BorderStroke? = null,
    containerColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit = { DefaultAdditionDeviceCardContent() },
) {
    Card(
        onClick = onClick,
        border = borderStroke,
        modifier =
            modifier.fillMaxHeight(0.15f)
                .fillMaxWidth(0.25f),
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor,
            ),
        shape = RoundedCornerShape(24.dp),
        content = content,
    )
}

@Composable
private fun DefaultAdditionDeviceCardContent() {
    Column(
        modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bracelet),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight(0.5f),
        )
        Text(
            "Браслет XL",
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PreviewAdditionalDeviceCard() {
    SangelTheme {
        AdditionalDeviceCard(onClick = {})
    }
}
