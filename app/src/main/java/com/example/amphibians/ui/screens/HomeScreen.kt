package com.example.amphibians.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.amphibians.R
import com.example.amphibians.ui.theme.AmphibiansTheme


@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier : Modifier = Modifier
){
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibianUiState.Success -> ResultScreen(amphibianUiState.details, modifier = modifier.fillMaxWidth())
        else -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun AmphibianCard(modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium

    ) {
        Text(
            text = "title",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(12.dp))

        Image(painterResource(id = R.drawable.frog) , contentDescription = null )

        Text(text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            fontWeight = FontWeight.Medium,
            modifier = modifier.padding(12.dp)
            )
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
fun ErrorScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "")
        Text(text = "Failed to load try again later", modifier = Modifier.padding(16.dp))
//        Button(onClick = retryAction){
//            Text("Try Again")
//        }
    }
}

//@Preview(showBackground = false)
//@Composable
//fun AmphibianScreenPreview(){
//    AmphibiansTheme{
//        AmphibianCard()
//    }
//}