package com.github.kimamik.greenbox.presentation.onboarding

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.ui.theme.components.BlurredButton
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreenRoot(
    acceptOnboarding: () -> Unit
) {
    Scaffold { padding ->
        OnboardingScreen(
            acceptOnboarding = acceptOnboarding,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}

@Composable
fun OnboardingScreen(
    acceptOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(100.dp))
        Text(
            text = stringResource(R.string.onboarding_title),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        TagsFlow(
            courses = courses(),
            modifier = Modifier
                .fillMaxWidth()
                .height(316.dp)
        )

        Spacer(Modifier.weight(1f))
        FilledIconButton(
            onClick = acceptOnboarding,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.action_continue))
        }
    }
}

data class CourseButton(
    val text: String,
    val rotation: Float = ROTATION_NONE,
    val rotationPoint: RotationPoint = RotationPoint.TOP
) {
    enum class RotationPoint {
        TOP,
        BOTTOM
    }

    companion object {
        const val ROTATION_NONE = 0.0f
    }
}

data class CourseRow(val buttons: List<CourseButton>) {
    val expanded = buttons.any { it.rotation != CourseButton.ROTATION_NONE }
}

data class Courses(val rows: List<CourseRow>)

@Composable
private fun courses() = remember<Courses> {
    val row1 = listOf(
        CourseButton("1С Администрирование"),
        CourseButton("RabbitMQ", rotation = -15f),
        CourseButton("Трафик"),
    )

    val row2 = listOf(
        CourseButton("Контент маркетинг"),
        CourseButton("B2B маркетинг"),
        CourseButton("Google Аналитика")
    )

    val row3 = listOf(
        CourseButton("UX исследователь"),
        CourseButton("Веб-аналитика"),
        CourseButton("Big Data", rotation = 15f)
    )

    val row4 = listOf(
        CourseButton("Геймдизайн"),
        CourseButton("Веб-дизайн"),
        CourseButton("Cinema 4D"),
        CourseButton("Промпт инженеринг")
    )

    val row5 = listOf(
        CourseButton("Webflow"),
        CourseButton(
            "Three.js",
            rotation = -15f,
            rotationPoint = CourseButton.RotationPoint.BOTTOM
        ),
        CourseButton("Парсинг"),
        CourseButton("Python-разработка"),
    )

    Courses(
        listOf(
            CourseRow(row1),
            CourseRow(row2),
            CourseRow(row3),
            CourseRow(row4),
            CourseRow(row5)
        )
    )
}

@Composable
private fun TagsFlow(
    courses: Courses,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .horizontalScroll(scrollState)
            .onPlaced { layoutCoordinates ->
                coroutineScope.launch {
                    val contentWidth = layoutCoordinates.size.width
                    val screenWidth = scrollState.viewportSize
                    val amountToScroll = (contentWidth - screenWidth) / 2
                    scrollState.scrollTo(amountToScroll)
                }
            },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in courses.rows) {
            ActualRow(row)
        }
    }
}

@Composable
fun OnboardingButton(
    button: CourseButton,
    modifier: Modifier = Modifier
) {
    when {
        button.rotation == CourseButton.ROTATION_NONE -> {
            BlurredButton(
                onClick = {},
                modifier = modifier,
                blurRadius = 21f
            ) {
                Text(text = button.text)
            }
        }

        else -> {
            Button(
                onClick = {},
                modifier = modifier
                    .graphicsLayer(
                        rotationZ = button.rotation,
                        transformOrigin = TransformOrigin(
                            pivotFractionX =
                                when (button.rotationPoint) {
                                    CourseButton.RotationPoint.TOP -> 1f
                                    CourseButton.RotationPoint.BOTTOM -> 0.0f
                                },
                            pivotFractionY = 0.5f
                        )
                    )
            ) {
                Text(text = button.text)
            }
        }

    }
}

@Composable
fun ActualRow(
    row: CourseRow,
    modifier: Modifier = Modifier
) = Row(
    modifier
        .zIndex(if (row.expanded) 0f else 1f),
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (button in row.buttons) {
        OnboardingButton(
            button = button,
            modifier = Modifier
                .height(if (row.expanded) 60.dp else 52.dp)
        )
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() = GBPreview {
    OnboardingScreen(
        acceptOnboarding = {},
        modifier = Modifier.fillMaxSize()
    )
}