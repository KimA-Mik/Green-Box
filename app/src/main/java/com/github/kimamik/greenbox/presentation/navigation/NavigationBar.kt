package com.github.kimamik.greenbox.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.kimamik.greenbox.presentation.util.GBPreview
import com.github.kimamik.greenbox.presentation.util.LocalNavController

@Composable
fun GBNavBar(
    modifier: Modifier = Modifier
) = Column(modifier) {
    val navController = LocalNavController.current
    HorizontalDivider()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        NavItem.entries.forEach { navItem ->
            val title = stringResource(navItem.titleId)
            val selected = currentDestination?.hierarchy?.any { it.route == navItem.root } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(navItem.root) {
                        launchSingleTop = true
                    }
                },
                icon = { Icon(painterResource(navItem.iconId), contentDescription = title) },
                label = { Text(text = title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun GbNavBarPreview() = GBPreview {
    val navController = rememberNavController()
    navController.navigate(NavItem.entries.first().root) {
        launchSingleTop = true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { GBNavBar() }
    ) {
        Box(Modifier.padding(it))
    }
}