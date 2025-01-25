package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@Composable
fun WoofApp() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_woof_logo),
                contentDescription = "Woof Logo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Woof",
                fontFamily = FontFamily(Font(R.font.abrilfatface_regular)),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Liste des chiens
        val dogList = listOf(
            Dog("Koda", "2 years old", "About", R.drawable.koda, "Editing treats on the terrace"),
            Dog("Lola", "16 years old", "About", R.drawable.lola, "Barking at Daddy"),
            Dog("Frankie", "2 years old", "", R.drawable.frankie, ""),
            Dog("Nox", "8 years old", "", R.drawable.nox, ""),
            Dog("Faye", "8 years old", "", R.drawable.faye, ""),
            Dog("Bella", "14 years old", "", R.drawable.bella, "")
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp) // Ajouter un espacement entre les éléments
        ) {
            itemsIndexed(dogList) { index, dog ->
                DogItem(dog = dog, index = index)
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, index: Int) {
    val cardHeight = if (index < 2) 140.dp else 120 .dp
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f) 
            .padding(horizontal = 16.dp, vertical = 4.dp)  
            .height(cardHeight),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f)) 
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Ajuster le padding pour aligner les éléments
            verticalAlignment = Alignment.Top, // Aligner les éléments en haut
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = dog.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) // Ajouter un espace entre l'image et le texte
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = dog.name,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = dog.age,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp
                )
                Text(
                    text = dog.about,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontSize = 12.sp
                )
            }
        }
    }
}

data class Dog(val name: String, val age: String, val about2: String, val imageRes: Int, val about: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoofTheme {
        WoofApp()
    }
}
