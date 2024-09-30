package ru.sangel.zaya.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.zaya.presentation.entities.AboutTeamEntity
import ru.sangel.common.ui.theme.SangelTheme

@Composable
fun AboutTeamItem(
    aboutTeamEntity: AboutTeamEntity,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        ProfileImage(image = aboutTeamEntity.tgId.getProfilePhoto(), modifier = Modifier.size(40.dp))
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
        AboutTeamItem(aboutTeamEntity = AboutTeamEntity("test", "test1", "asda", ""))
    }
}

@Composable
private fun String.getProfilePhoto(): Painter? = when(this.lowercase()){
    "dany_217mk" -> painterResource(id = R.drawable.dany_217mk)
    "mezhendosina" -> painterResource(id = R.drawable.mezhendosina)
    "baidyshev" -> painterResource(id = R.drawable.baidyshev)
    "iamceo05" -> painterResource(id = R.drawable.iamceo05)
    else -> null
}