package gr.aposalo.stylish.ui.composables.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gr.aposalo.stylish.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadedView(
    loading:Boolean,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalRippleConfiguration provides noRipple){
        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize().background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.size(size = 64.dp),
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(width = 8.dp))

                Text(
                    text = stringResource(id = R.string.please_wait),
                    color = Color.Black
                )
            }
        } else content()
    }

}