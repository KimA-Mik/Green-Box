package com.github.kimamik.greenbox.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.ui.theme.components.GBTextField

@Composable
fun AuthInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) = Column(
    modifier = modifier.width(IntrinsicSize.Min),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
    GBTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        placeholder = {
            placeholder?.let {
                Text(it)
            }
        }
    )
}

@Preview
@Composable
private fun AuthInputPreview() = GBPreview {
    AuthInput(
        title = "Title",
        value = "Value",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}