package com.github.kimamik.greenbox.presentation.courses.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kimamik.greenbox.R
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.ui.theme.Glass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Course(
    course: DisplayCourse,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        CardHeader(
            rate = course.rate,
            timestamp = course.startDate,
            bookmarked = course.hasLike,
            onBookmarkClick = onBookmarkClick,
            modifier = Modifier
                .clip(CardDefaults.shape)
                .fillMaxWidth()
                .height(114.dp)
        )
        CardContent(
            title = course.title,
            text = course.text,
            price = course.price,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun CardContent(
    title: String,
    text: String,
    price: String,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {

    Text(
        title,
        modifier = Modifier.align(Alignment.Start),
        style = MaterialTheme.typography.titleMedium
    )
    CardContentBody(text, price)
}

@Composable
fun CardHeader(
    rate: String,
    timestamp: Long,
    bookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
) {
    Image(
        painterResource(R.drawable.placeholder_image), null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    HeaderSurface(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
            .size(28.dp)
            .clickable { onBookmarkClick() },
        shape = CircleShape
    ) {
        Icon(
            painterResource(
                id = if (bookmarked) R.drawable.icon_bookmark_fill
                else R.drawable.icon_navigation_bookmark
            ), null,
            modifier = Modifier.size(16.dp),
            tint = if (bookmarked) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onPrimary
        )
    }

    HeaderFooter(
        rate = rate,
        timestamp = timestamp,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.BottomStart)
    )

}

@Composable
fun HeaderFooter(
    rate: String,
    timestamp: Long,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    HeaderSurface {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star, contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(rate, style = MaterialTheme.typography.labelSmall)
        }
    }

    val context = LocalContext.current
    val text = remember(timestamp) {
        val locale = context.resources.configuration.locales[0]
        val sdf = SimpleDateFormat("d MMMM yyyy", locale)
        val date = Date(timestamp)
        sdf.format(date)
    }
    HeaderSurface {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 4.dp)
                .width(IntrinsicSize.Max),
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun HeaderSurface(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    content: @Composable () -> Unit
) {
    val hazeState = remember { HazeState() }
    Box(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
        )

        Box(
            Modifier
                .clip(shape)
                .fillMaxSize()
                .hazeEffect(hazeState, style = HazeMaterials.thick(Glass)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun CardContentBody(
    text: String,
    price: String,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    Text(
        text,
        modifier = Modifier.alpha(0.7f),
        maxLines = 2,
        style = MaterialTheme.typography.bodySmall,
        overflow = TextOverflow.Ellipsis
    )

    CourseBottomRow(price, modifier = Modifier.fillMaxWidth())
}

@Composable
fun CourseBottomRow(
    price: String,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
) {
    Text(stringResource(R.string.rouble_format, price))

    Spacer(Modifier.weight(1f))
    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.primary
    ) {
        val title = stringResource(R.string.action_course_details)
        Text(title, style = MaterialTheme.typography.labelLarge)
        Icon(painterResource(R.drawable.icon_arrow_right_short_fill), contentDescription = title)
    }
}

@Preview
@Composable
private fun CoursePreview() = GBPreview {
    Course(
        course = DisplayCourse(
            id = 1,
            title = "Java-разработчик с нуля",
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
            price = "999",
            rate = "4.9",
            startDate = 1716325200000,
            publishDate = 1706821200000,
            hasLike = true
        ),
        onBookmarkClick = {},
        modifier = Modifier
            .width(328.dp)
            .padding(16.dp)
    )
}