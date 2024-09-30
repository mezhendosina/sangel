package ru.sangel.zaya.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sangel.common.ui.theme.SangelTheme

@Composable
fun CodeField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,

    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    val destiny = LocalDensity.current
    var componentWidth by remember {
        mutableStateOf(0.dp)
    }
    val size = (componentWidth.value.dp / length.dp).dp - 8.dp
    val fontSize = with(destiny) { (size - 8.dp).toSp() }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { with(destiny) { componentWidth = it.size.width.toDp() } },
        value = value,
        singleLine = true,
        onValueChange = {
            if (it.length <= length) {
                onValueChange(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Box {
                // hide the inner text field as we are dwelling the text field ourselves
                CompositionLocalProvider(
                    LocalTextSelectionColors.provides(
                        TextSelectionColors(
                            Color.Transparent,
                            Color.Transparent,
                        ),
                    ),
                ) {
                    Box(modifier = Modifier.drawWithContent { }) {
                        innerTextField()
                    }
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    repeat(length) { index ->
                        // if char is not null, show it, otherwise show empty box
                        val currentChar = value.getOrNull(index)
                        Box(
                            modifier =
                            modifier
                                .size(size)
                                .background(
                                    MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(8.dp),
                                ),
                        ) {
                            if (currentChar != null) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = currentChar.toString(),
                                        fontSize = fontSize
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
    )
}

@Preview(device = "id:Nexus One")
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:pixel_fold")
@Composable
private fun PreviewCodeField() {
    SangelTheme {
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {

            CodeField("123", 6) {}
        }
    }
}
