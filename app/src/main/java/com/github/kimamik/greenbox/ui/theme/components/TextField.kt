package com.github.kimamik.greenbox.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kimamik.greenbox.presentation.util.GBPreview

@Composable
fun GBTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = MaterialTheme.shapes.extraLarge
) {
    val ts = textStyle.merge(TextStyle(color = textColor))
    BasicTextField(
        value = value, onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = ts,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(ts.color),
        decorationBox = { innerTextField ->
            Surface(
                shape = shape,
                color = MaterialTheme.colorScheme.surfaceContainerHighest
            ) {
                Row(
                    modifier = Modifier
                        .padding(TextFieldDefaults.contentPaddingWithoutLabel()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.invoke()

                    Box(
                        modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))
                    ) {
                        innerTextField()
                        if (value.isEmpty()) {
                            ProvideTextStyle(ts) {
                                Box(modifier = Modifier.alpha(0.5f)) {
                                    placeholder?.invoke()
                                }
                            }
                        }
                    }

                    trailingIcon?.invoke()
                }
            }
        }
    )
}

@Preview
@Composable
private fun GbTextFieldPreview() = GBPreview {
    GBTextField(
        value = "Green Box",
        onValueChange = {}
    )
}

@Preview
@Composable
private fun GbTextFieldWithPlaceholderPreview() = GBPreview {
    GBTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Placeholder") }
    )
}