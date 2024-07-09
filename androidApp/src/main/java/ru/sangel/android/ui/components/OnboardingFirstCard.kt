package ru.sangel.android.ui.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.android.R
import ru.sangel.android.ui.theme.SangelTheme

@Composable
fun OnboardingFirstCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.sangel_its),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_first_onboarding),
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(120.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            stringResource(R.string.go_home_without_stress),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(backgroundColor = 0xFFF1C064, showBackground = true)
@Composable
private fun PreviewOnboardingFirstCard() {
    SangelTheme {
        OnboardingFirstCard()
    }
}