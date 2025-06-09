package com.github.kimamik.greenbox.presentation.courses.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.courses.components.Course
import com.github.kimamik.greenbox.presentation.navigation.GBNavBar
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.presentation.util.LocalHazeState
import com.github.kimamik.greenbox.ui.theme.components.GBTextField
import dev.chrisbanes.haze.HazeState

@Composable
fun CoursesScreenRoot() {
    val viewModel: CourseScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    CoursesScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CoursesScreen(
    state: CourseScreenState,
    onEvent: (CourseScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val hazeState = remember { HazeState() }
    Scaffold(
        modifier = modifier,
        bottomBar = { GBNavBar() }
    ) { paddingValues ->
        CompositionLocalProvider(LocalHazeState provides hazeState) {
            CourseScreenContent(
                state = state,
                onEvent = onEvent,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun CourseScreenContent(
    state: CourseScreenState,
    onEvent: (CourseScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Search()
        Sort(modifier = Modifier.align(Alignment.End))
        CourseScreenBody(
            state = state,
            onEvent = onEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Search(modifier: Modifier = Modifier) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {

    GBTextField(
        value = "", onValueChange = {},
        modifier = Modifier.weight(1f),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = {
            Text(stringResource(R.string.hint_search_courses))
        })

    IconButton(
        onClick = {},
        Modifier.size(56.dp),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {
        Icon(painterResource(R.drawable.icon_funnel), contentDescription = null)
    }
}

@Composable
fun Sort(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
) {
    CompositionLocalProvider(
        LocalContentColor provides color
    ) {
        Text(
            stringResource(R.string.label_sort_by_date),
            style = MaterialTheme.typography.labelLarge
        )
        Icon(painterResource(R.drawable.icon_arrow_down_up), contentDescription = null)
    }
}

@Composable
fun CourseScreenBody(
    state: CourseScreenState,
    onEvent: (CourseScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        CourseScreenState.Error -> {}
        is CourseScreenState.Loaded -> Courses(
            state = state,
            onEvent = onEvent,
            modifier = modifier
        )

        CourseScreenState.Loading -> Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun Courses(
    state: CourseScreenState.Loaded,
    onEvent: (CourseScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.courses, key = { it.id }) {
            Course(
                course = it,
                onBookmarkClick = { onEvent(CourseScreenUserEvent.CheckBookmark(it.id)) }
            )
        }
    }
}

@Preview
@Composable
private fun CourseScreenPreview() = GBPreview {
    CoursesScreen(
        state = CourseScreenState.Loading,
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}