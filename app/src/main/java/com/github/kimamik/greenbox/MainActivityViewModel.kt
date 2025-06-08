package com.github.kimamik.greenbox

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kimamik.greenbox.domain.local.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
) : ViewModel() {
    val showOnboarding = localDataSource
        .localProperties()
        .map { it.showOnboarding }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun acceptOnboarding() {
        viewModelScope.launch {
            localDataSource.updateShowOnboarding(false)
        }
    }
}