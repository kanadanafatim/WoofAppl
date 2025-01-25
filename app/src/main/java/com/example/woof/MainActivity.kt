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

            )
        }

        // Liste des chiens
        val dogList = listOf(
            Dog("Koda", "About", "2 years old", R.drawable.koda, "Editing treats on the terrace"),
            Dog("Lola","About", "16 years old", R.drawable.lola, "Barking at Daddy"),
            Dog("Frankie","About", "2 years old", R.drawable.frankie, ""),
            Dog("Nox", "About","8 years old", R.drawable.nox, ""),
            Dog("Faye", "About","8 years old", R.drawable.faye, ""),
            Dog("Bella", "About","14 years old", R.drawable.bella, "")
        )

        LazyColumn {
            itemsIndexed(dogList) { index, dog ->
                DogItem(dog = dog, index = index)
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, index: Int) {
    val cardHeight = if (index < 2) 120.dp else 80.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(cardHeight)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = dog.name,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = dog.about2,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
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
                    fontSize = 14.sp
                )
            }

        }
    }
}

data class Dog(val name: String, val about2: String, val age: String, val imageRes: Int, val about: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoofTheme {
        WoofApp()
    }
}
