package com.github.kimamik.greenbox.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.kimamik.greenbox.R

enum class NavItem(
    val root: Rout,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    Main(
        root = Rout.Main.Courses,
        titleId = R.string.nav_item_main,
        iconId = R.drawable.icon_navigation_home
    ),
    Favorites(
        root = Rout.Main.Favorites,
        titleId = R.string.nav_item_favorites,
        iconId = R.drawable.icon_navigation_bookmark
    ),
    Account(
        root = Rout.Main.Profile,
        titleId = R.string.nav_item_account,
        iconId = R.drawable.icon_navigation_person
    )
}
