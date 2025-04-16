package gr.aposalo.stylish.ui.composables.shared

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
val noRipple = RippleConfiguration(
    color = Color.Unspecified,
    rippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
)