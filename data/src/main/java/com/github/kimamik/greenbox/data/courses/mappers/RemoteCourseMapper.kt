package com.github.kimamik.greenbox.data.courses.mappers

import com.github.kimamik.greenbox.data.courses.model.RemoteCourseDto
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import java.text.SimpleDateFormat

fun RemoteCourseDto.toCourse(simpleDateFormat: SimpleDateFormat): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = simpleDateFormat.parse(startDate)?.time ?: 0L,
        publishDate = simpleDateFormat.parse(publishDate)?.time ?: 0L,
        hasLike = hasLike
    )
}
