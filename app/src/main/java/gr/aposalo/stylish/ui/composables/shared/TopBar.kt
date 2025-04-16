package gr.aposalo.stylish.ui.composables.shared

import android.hardware.camera2.params.BlackLevelPattern
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gr.aposalo.stylish.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title:String,
    onBackClick: () -> Unit={},
    topBarContainerColor: Color = Color.White,
    containerColor: Color = Color.White,
    textColor:Color= Color.White,
    action: @Composable (RowScope.() -> Unit) = {},
    hasNavigation:Boolean = true,
    isLoading: Boolean = false,
    toolbarContent: @Composable (paddingValues: PaddingValues) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize().height(50.dp),
        containerColor = if (isLoading) Color.White else containerColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .width(92.dp)
                                .height(12.dp)
                                .clip(CircleShape)
                        )
                    }
                    else {
                        Text(
                            text = title,
                            color = textColor,
                            style = Typography.titleMedium,
                        )
                    }
                },
                navigationIcon = {
                    if (!isLoading && hasNavigation) {
                        IconButton(onClick = { onBackClick() }) {
                            Image(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "BackArrow"
                            )
                        }
                    }
                },
                colors = topAppBarColors(
                    containerColor = if (isLoading) Color.White else topBarContainerColor
                ),
                actions = action
            )
        }
    ){
        toolbarContent(it)
    }


}