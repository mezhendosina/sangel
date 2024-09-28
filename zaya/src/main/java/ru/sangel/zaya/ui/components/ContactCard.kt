package ru.sangel.zaya.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.zaya.ui.theme.SangelTheme

@Composable
fun ContactCard(
    name: String,
    checked: Boolean,
    profileIcon: Painter? = null,
    onClick: () -> Unit,
) {
    OutlinedCard(
        shape = RoundedCornerShape(64.dp),
        colors =
            CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        onClick = onClick,
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Image(
                    if (profileIcon == null) painterResource(id = R.drawable.ic_avatar) else profileIcon,
                    null,
                    modifier = Modifier.size(32.dp),
                )
                Text(name)
            }
            Image(
                painterResource(
                    id = if (checked) R.drawable.ic_star_filled else R.drawable.ic_star_outlined,
                ),
                if (checked) stringResource(id = R.string.fav_contacts) else null,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContactCard() {
    SangelTheme {
        ContactCard(
            name = "123",
            profileIcon = painterResource(id = R.drawable.ic_logo),
            checked = false,
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContactCard1() {
    SangelTheme {
        ContactCard(
            name = "123",
            profileIcon = painterResource(id = R.drawable.ic_logo),
            checked = true,
        ) {}
    }
}
