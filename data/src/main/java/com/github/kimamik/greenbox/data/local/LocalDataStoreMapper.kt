package com.github.kimamik.greenbox.data.local

import com.github.kimamik.greenbox.domain.local.LocalProperties

fun LocalSchema.toLocalProperties() = LocalProperties(
    showOnboarding = showOnboarding,
    favoriteCourses = favoriteCourses
)