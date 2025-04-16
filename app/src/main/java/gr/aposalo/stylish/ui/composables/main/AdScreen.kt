package gr.aposalo.stylish.ui.composables.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gr.aposalo.stylish.R
import gr.aposalo.stylish.ui.composables.shared.DotsIndicator
import gr.aposalo.stylish.ui.theme.Typography
import kotlinx.coroutines.runBlocking

@Composable
fun AdScreen(){

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = {
            3
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            pageSize = PageSize.Fill,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
            ) {

                Image(
                    modifier = Modifier.size(343.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "50-40% OFF",
                        style = Typography.headlineSmall,
                        color = colorScheme.surface
                    )
                    Text(
                        text = "Now in (product)",
                        style = Typography.labelSmall,
                        color = colorScheme.surface
                    )
                    Text(
                        text = "All colours",
                        style = Typography.labelSmall,
                        color = colorScheme.surface
                    )
                    Image(
                        modifier = Modifier.width(100.dp).height(32.dp),
                        painter = painterResource(id = R.drawable.ic_shop_now),
                        contentDescription = null
                    )
                }
            }
        }
    }

    DotsIndicator(
        totalDots = 3,
        selectedIndex = pagerState.currentPage,
        selectedColor = colorScheme.onBackground,
        unSelectedColor = White,
        clickedImage = { target ->
            runBlocking {
                pagerState.scrollToPage(target)
            }
        })
}