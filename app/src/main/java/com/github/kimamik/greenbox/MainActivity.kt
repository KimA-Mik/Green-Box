package com.github.kimamik.greenbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.kimamik.greenbox.presentation.onboarding.OnboardingScreenRoot
import com.github.kimamik.greenbox.ui.theme.GreenBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenBoxTheme {
                OnboardingScreenRoot()
            }
        }
    }
}