package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        ArtItem(R.drawable.art1, "La Nuit étoilée", "Vincent Van Gogh", "1889"),
        ArtItem(R.drawable.art2, "Monna Lisa", "Léonard de Vinci", "1503-1506"),
        ArtItem(R.drawable.art3, "Self-portrait with Straw Hat", "Vincent Van Gogh", "1881–1885")
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artworks[currentIndex].imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display artwork details
        ArtworkDetails(artworks[currentIndex])

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
            }) {
                Text("Previous")
            }

            Button(onClick = {
                currentIndex = (currentIndex + 1) % artworks.size
            }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ArtworkDetails(artItem: ArtItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = artItem.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = artItem.artist, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        Text(text = artItem.year, style = MaterialTheme.typography.bodyMedium)
    }
}

data class ArtItem(val imageRes: Int, val title: String, val artist: String, val year: String)

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
