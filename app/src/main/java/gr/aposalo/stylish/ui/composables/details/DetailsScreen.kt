package gr.aposalo.stylish.ui.composables.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import gr.aposalo.stylish.R
import gr.aposalo.stylish.ui.composables.shared.TopBar
import gr.aposalo.stylish.ui.theme.Typography

@Composable
fun DetailsScreen(
    id: String,
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
){

    val isLoading = viewModel.isLoading.collectAsState().value
    val product = viewModel.product.collectAsState().value

    LaunchedEffect(Unit){
        viewModel.getProduct(id)
    }

    TopBar(
        title = "",
        onBackClick = onBackClick,
        action = {
            Image(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = ""
            )
        },
        isLoading = isLoading
    ){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 28.dp),
        ){
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(213.dp),
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = product.title,
                style = Typography.headlineMedium,
                color = Black
            )
            Text(
                text = product.category,
                style = Typography.bodyMedium,
                color = Black
            )
            Text(
                text = product.price + "€",
                style = Typography.labelLarge,
                color = Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Product Details",
                style = Typography.labelLarge,
                color = Black
            )

            Text(
                text = product.description,
                style = Typography.bodyMedium,
                color = Black
            )

        }



    }

}