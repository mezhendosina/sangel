package ru.sangel.zaya.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.common.ui.theme.SangelTheme

@Composable
fun OnboardingFirstCard(modifier: Modifier = Modifier) {
        Image(
            painterResource(id = R.drawable.ic_first_onboarding),
            null,
            modifier =
            Modifier
                .safeDrawingPadding()
                .padding(28.dp)
                .fillMaxWidth(),
        )
}

@Preview(backgroundColor = 0xFFF1C064, showBackground = true)
@Composable
private fun PreviewOnboardingFirstCard() {
    SangelTheme {
        OnboardingFirstCard()
    }
}