package com.github.kimamik.greenbox.presentation.courses.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.courses.components.Course
import com.github.kimamik.greenbox.presentation.navigation.GBNavBar
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.presentation.util.LocalHazeState
import dev.chrisbanes.haze.HazeState

@Composable
fun FavoriteCoursesScreenRoot() {
    val viewModel: FavoriteCoursesScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    FavoriteCoursesScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun FavoriteCoursesScreen(
    state: FavoriteCoursesScreenState,
    onEvent: (FavoriteCoursesScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val hazeState = remember { HazeState() }
    CompositionLocalProvider(
        LocalHazeState provides hazeState
    ) {
        Scaffold(
            modifier = modifier,
            bottomBar = { GBNavBar() }
        ) { paddingValues ->
            FavoriteCoursesScreenContent(
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
private fun FavoriteCoursesScreenContent(
    state: FavoriteCoursesScreenState,
    onEvent: (FavoriteCoursesScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    Text(
        text = stringResource(R.string.title_favorite_courses),
        style = MaterialTheme.typography.titleLarge
    )
    FavoriteScreenContent(
        state = state,
        onEvent = onEvent,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun FavoriteScreenContent(
    state: FavoriteCoursesScreenState,
    onEvent: (FavoriteCoursesScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        FavoriteCoursesScreenState.Error -> {}
        is FavoriteCoursesScreenState.Loaded -> Courses(
            state = state,
            onEvent = onEvent,
            modifier = modifier
        )

        FavoriteCoursesScreenState.Loading -> Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun Courses(
    state: FavoriteCoursesScreenState.Loaded,
    onEvent: (FavoriteCoursesScreenUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.courses, key = { it.id }) {
            Course(
                course = it,
                onBookmarkClick = { onEvent(FavoriteCoursesScreenUserEvent.UnmarkCourse(it.id)) }
            )
        }
    }
}

@Preview
@Composable
private fun FavoriteCourseScreenPreview() = GBPreview {
    FavoriteCoursesScreen(
        state = FavoriteCoursesScreenState.Loading,
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}