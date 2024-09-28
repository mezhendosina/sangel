package ru.sangel.zaya.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.zaya.presentation.entities.DeviceUiEntity

@Composable
fun MainDeviceCard(
    device: DeviceUiEntity,
    onClick: (address: String) -> Unit,
) {
    Card(
        onClick = { onClick.invoke(device.macAddress) },
        modifier = Modifier.fillMaxWidth(0.7f),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFAEB884)),
        shape = RoundedCornerShape(32.dp),
    ) {
        Box(
            modifier =
                Modifier.background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xFFF2EBD8),
                        ),
                    ),
                ),
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bracelet),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .padding(horizontal = 16.dp),
                )
                Spacer(modifier = Modifier.size(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = device.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A),
                    )
                    Image(
                        painterResource(id = R.drawable.ic_battery),
                        null,
                        modifier = Modifier.height(24.dp),
                    )
                }
            }
        }
    }
}
//
// @Preview
// @Composable
// private fun PreviewMainDeviceCard() {
//    SangelTheme {
//        MainDeviceCard {
// //
//        }
//    }
// }
