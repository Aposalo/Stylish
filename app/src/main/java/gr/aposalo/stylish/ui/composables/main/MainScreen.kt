package gr.aposalo.stylish.ui.composables.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import gr.aposalo.stylish.R
import gr.aposalo.stylish.ui.composables.shared.LoadedView
import gr.aposalo.stylish.ui.theme.Typography
import kotlin.text.Typography.euro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onProductDetailsClicked: (String) -> Unit,
){

    val isLoading = viewModel.isLoading.collectAsState().value
    val titles = viewModel.titles.collectAsState().value
    val products = viewModel.products.collectAsState().value

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { products.size })


    LoadedView(
        loading = isLoading,
    ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = White)
                    .padding(horizontal = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_top_bar),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    items(titles.size){
                        Column {
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .size(56.dp)
                                    .clip(CircleShape),
                            )
                            Text(
                                text = titles[it],
                                style = Typography.bodySmall,
                                color = colorScheme.onSurface
                            )
                        }
                    }
                }


                AdScreen()

                Column(
                    modifier = Modifier.fillMaxWidth().height(241.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    HorizontalPager(
                        pageSpacing = 15.dp,
                        pageSize = PageSize.Fixed(171.dp),
                        state = pagerState,
                        verticalAlignment = Alignment.CenterVertically
                    ) { selectedIndex ->
                        Column(
                            modifier = Modifier.clickable{
                                onProductDetailsClicked(products[selectedIndex].id.toString())
                            },
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){

                            AsyncImage(
                                modifier = Modifier.width(170.dp).height(124.dp),
                                model = products[selectedIndex].image,
                                contentDescription = null
                            )

                            Text(
                                text = products[selectedIndex].title,
                                style = Typography.labelMedium,
                                color = Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = products[selectedIndex].description,
                                style = Typography.bodySmall,
                                color = Black,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,

                            )

                            Text(
                                text =  "${products[selectedIndex].price}$euro",
                                style = Typography.labelMedium,
                                color = Black
                            )

                        }
                    }
                }
            }
        }

}