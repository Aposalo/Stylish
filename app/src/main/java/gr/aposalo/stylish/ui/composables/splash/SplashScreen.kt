package gr.aposalo.stylish.ui.composables.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import gr.aposalo.stylish.R
import gr.aposalo.stylish.graph.Graph
import gr.aposalo.stylish.ui.composables.shared.noRipple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onAnimationFinished: (String) -> Unit
){
    var visible by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()
    var isAnimationCancelled by remember { mutableStateOf(false) }
    var skipAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(isAnimationCancelled) {
        if (!isAnimationCancelled) {
            delay(1000)
            visible = true
            delay(3000L)
            onAnimationFinished(
                if(viewModel.hasAccessToken())
                    Graph.HOME
                else
                   Graph.LOGIN
            )
        }
    }

    CompositionLocalProvider(LocalRippleConfiguration provides noRipple) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable {
                if(!isAnimationCancelled) {
                    coroutineScope.launch {
                        isAnimationCancelled = true
                        skipAnimation = true
                        visible = true
                        delay(500)
                        onAnimationFinished(
                            if(viewModel.hasAccessToken())
                                Graph.HOME
                            else
                                Graph.LOGIN
                        )
                    }
                }
            }
        ) {

            if (skipAnimation) {
                // Show content instantly without animation
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_init_screen),
                        contentDescription = "splash image",
                        contentScale = ContentScale.Fit,
                    )
                }
            } else {

                AnimatedVisibility(
                    visible = visible, modifier = Modifier.fillMaxSize(),
                    enter =
                        fadeIn(
                            tween(
                                durationMillis = 1000,
                                easing = FastOutSlowInEasing
                            )
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_init_screen),
                            contentDescription = "splash image",
                            contentScale = ContentScale.Fit,
                        )
                    }
                }
            }

        }
    }
}