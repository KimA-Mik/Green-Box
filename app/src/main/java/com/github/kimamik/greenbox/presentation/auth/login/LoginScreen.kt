package com.github.kimamik.greenbox.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.auth.components.AuthInput
import com.github.kimamik.greenbox.presentation.auth.components.OkButton
import com.github.kimamik.greenbox.presentation.auth.components.VkButton
import com.github.kimamik.greenbox.presentation.navigation.grapths.enterMainGraph
import com.github.kimamik.greenbox.presentation.util.Event
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.presentation.util.LocalNavController

@Composable
fun LoginScreenRoot() {
    val viewModel: LoginScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle()
    LoginScreen(
        state = state,
        uiEvent = uiEvent,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onEnterApp = viewModel::enterApp,
        clickVk = viewModel::clickVk,
        clickOk = viewModel::clickOk
    )
}

@Composable
fun LoginScreen(
    state: LoginScreenState,
    uiEvent: Event<LoginScreenViewModel.UiEvent>,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEnterApp: () -> Unit,
    clickVk: () -> Unit,
    clickOk: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val navController = LocalNavController.current
    LaunchedEffect(uiEvent) {
        uiEvent.consume { event ->
            when (event) {
                LoginScreenViewModel.UiEvent.OpenOk -> uriHandler.openUri("https://ok.ru")
                LoginScreenViewModel.UiEvent.OpenVk -> uriHandler.openUri("https://vk.com")
                LoginScreenViewModel.UiEvent.EnterApp -> navController.enterMainGraph()
            }
        }
    }

    Scaffold(
        modifier = modifier
    ) { padding ->
        LoginScreenContent(
            state = state,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onEnterApp = onEnterApp,
            clickVk = clickVk,
            clickOk = clickOk,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}

@Composable
fun LoginScreenContent(
    state: LoginScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEnterApp: () -> Unit,
    clickVk: () -> Unit,
    clickOk: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val m = Modifier.fillMaxWidth()
    Text(
        text = stringResource(R.string.login_page_title),
        modifier = m,
        style = MaterialTheme.typography.headlineLarge
    )

    Spacer(m.height(28.dp))
    Inputs(
        email = state.login,
        onEmailChange = onEmailChange,
        password = state.password,
        onPasswordChange = onPasswordChange,
        modifier = m
    )

    Spacer(m.height(24.dp))
    Button(
        onClick = onEnterApp,
        modifier = m,
        enabled = state.validated
    ) {
        Text(stringResource(R.string.action_login))
    }

    Spacer(m.height(16.dp))
    Actions(
        onRegisterClick = {},
        onForgetPasswordClick = {},
    )

    Spacer(m.height(32.dp))
    HorizontalDivider()

    Spacer(m.height(32.dp))
    Socials(
        clickVk = clickVk,
        clickOk = clickOk,
        modifier = m
    )
}

@Composable
fun Inputs(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    AuthInput(
        title = stringResource(R.string.email_title),
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = stringResource(R.string.email_placeholder),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
    )

    AuthInput(
        title = stringResource(R.string.password_title),
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = stringResource(R.string.password_placeholder),
        isPassword = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun Actions(
    onRegisterClick: () -> Unit,
    onForgetPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val textStyle = MaterialTheme.typography.labelLarge
    val urlStyle = SpanStyle(color = MaterialTheme.colorScheme.primary)

    Text(
        text = buildAnnotatedString {
            append(stringResource(R.string.label_no_accaunt))
            val actionRegister = stringResource(R.string.action_register)
            withLink(
                LinkAnnotation.Clickable(
                    tag = actionRegister,
                    styles = TextLinkStyles(style = urlStyle),
                ) { onRegisterClick() }) {
                append(actionRegister)
            }
        },
        style = textStyle
    )

    val actionForgetPassword = stringResource(R.string.action_forget_password)
    Text(
        text = buildAnnotatedString {
            withLink(
                LinkAnnotation.Clickable(
                    tag = actionForgetPassword,
                    styles = TextLinkStyles(style = urlStyle)
                ) { onForgetPasswordClick() }) {
                append(actionForgetPassword)
            }
        },
        style = textStyle
    )
}

@Composable
fun Socials(
    clickVk: () -> Unit,
    clickOk: () -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    val m = Modifier
        .height(40.dp)
        .weight(1f)
    VkButton(onClick = clickVk, modifier = m)
    OkButton(onClick = clickOk, modifier = m)
}

@Preview
@Composable
private fun LoginScreenPreview() = GBPreview {
    LoginScreen(
        state = LoginScreenState(),
        uiEvent = Event(null),
        onEmailChange = {},
        onPasswordChange = {},
        onEnterApp = {},
        clickVk = {},
        clickOk = {},
        modifier = Modifier.fillMaxSize()
    )
}
