package com.github.kimamik.greenbox.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.kimamik.greenbox.R

enum class NavItem(
    val root: String,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    Main(
        root = "main",
        titleId = R.string.nav_item_main,
        iconId = R.drawable.icon_navigation_home
    ),
    Favorites(
        root = "favorites",
        titleId = R.string.nav_item_favorites,
        iconId = R.drawable.icon_navigation_bookmark
    ),
    Account(
        root = "account",
        titleId = R.string.nav_item_account,
        iconId = R.drawable.icon_navigation_person
    )
}
