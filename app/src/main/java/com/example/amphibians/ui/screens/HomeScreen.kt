package com.example.amphibians.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianDetails
import com.example.amphibians.ui.theme.AmphibiansTheme


@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier : Modifier = Modifier
){
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibianUiState.Success -> AmphibianColumnScreen (amphibianUiState.details, modifier = modifier.fillMaxWidth())
        else -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun AmphibianCard(amphibian : AmphibianDetails , modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = stringResource(id = R.string.amphibian_title),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

//        Image(painterResource(id = R.drawable.frog) , contentDescription = null )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = null,
            contentScale = ContentScale.FillWidth,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun AmphibianColumnScreen( amphibians : List<AmphibianDetails> ,modifier:Modifier = Modifier){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(items = amphibians, key = {amphibian -> amphibian.name} ){amphibian ->
            AmphibianCard(
                amphibian = amphibian, modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ResultScreen(amphibians: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = amphibians)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = "")
}

@Composable
fun ErrorScreen(retryAction: () -> Unit ,modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "")
        Text(text = "Failed to load try again later", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction){
            Text("Try Again")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(Modifier.fillMaxSize().size(200.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansListScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) {
            AmphibianDetails(
                "Lorem Ipsum - $it",
                "$it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                        " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                        " minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                        " ex ea commodo consequat.",
                imgSrc = ""
            )
        }
        AmphibianColumnScreen(mockData, Modifier.fillMaxSize())
    }
}