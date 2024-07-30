package ru.sangel.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.android.R
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.entities.AboutTeamEntity

@Composable
fun AboutTeamItem(
    aboutTeamEntity: AboutTeamEntity,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
        if (aboutTeamEntity.photo != null) {
            Image(
                aboutTeamEntity.photo!!.asImageBitmap(),
                null,
            )
        } else {
            Image(
                painterResource(id = R.drawable.ic_avatar),
                null,
                modifier = Modifier.size(40.dp),
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                aboutTeamEntity.grade,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(aboutTeamEntity.name, style = MaterialTheme.typography.bodyLarge)
            Text(aboutTeamEntity.contact, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PreviewAboutTeamItem() {
    SangelTheme {
        AboutTeamItem(aboutTeamEntity = AboutTeamEntity("test", "test1", "asda", null))
    }
}
