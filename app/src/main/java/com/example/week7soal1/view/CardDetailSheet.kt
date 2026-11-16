package com.example.week7soal1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week7soal1.model.PokemonCard

@Composable
fun CardDetailSheet(
    card: PokemonCard,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = BgColor,
        shape = RoundedCornerShape(20.dp),
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PokeRed, contentColor = Color.White)
            ) {
                Text("Done", fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = card.imageCdnUrl400 ?: card.imageCdnUrl200,
                    contentDescription = card.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(240.dp).clip(RoundedCornerShape(12.dp))
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = card.name ?: "Unknown",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = card.setName ?: "-", color = TextMuted, fontSize = 13.sp)
                Spacer(Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    PriceTile(
                        label = "Market Price",
                        value = formatPrice(card.prices?.market),
                        valueColor = PriceGreen,
                        icon = Icons.Default.TrendingUp,
                        modifier = Modifier.weight(1f)
                    )
                    PriceTile(
                        label = "Lowest Price",
                        value = formatPrice(card.prices?.low),
                        valueColor = PriceYellow,
                        icon = Icons.Default.Sell,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(20.dp))
                SectionTitle("Market Activity")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceColor)
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    SpecRow("Active Listings", card.prices?.listings?.toString() ?: "-")
                    SpecRow("Verified Sellers", card.prices?.sellers?.toString() ?: "-")
                    SpecRow("Primary Printing", card.prices?.primaryPrinting ?: "-")
                }

                Spacer(Modifier.height(20.dp))
                SectionTitle("Card Specifications")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceColor)
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    SpecRow("Card Number", card.cardNumber ?: "-")
                    SpecRow("Rarity", card.rarity ?: "-")
                    SpecRow("Type", card.cardType ?: "-")
                    SpecRow("Illustrator", card.artist ?: "-")
                }
            }
        }
    )
}

@Composable
fun PriceTile(
    label: String,
    value: String,
    valueColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceColor)
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = valueColor, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(text = label, color = TextMuted, fontSize = 11.sp)
        }
        Spacer(Modifier.height(6.dp))
        Text(text = value, color = valueColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
    )
}

@Composable
fun SpecRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = TextMuted, fontSize = 12.sp)
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}