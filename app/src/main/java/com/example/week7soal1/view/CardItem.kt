package com.example.week7soal1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week7soal1.model.PokemonCard
import com.example.week7soal1.model.Prices
import java.util.Locale

fun formatPrice(value: Double?): String {
    if (value == null) return "-"
    return "$" + String.format(Locale.US, "%.2f", value)
}

@Composable
fun CardItem(
    card: PokemonCard,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceColor)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        AsyncImage(
            model = card.imageCdnUrl400 ?: card.imageCdnUrl200,
            contentDescription = card.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.72f)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceLight)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = card.name ?: "Unknown",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${card.setName ?: "-"} • #${card.cardNumber ?: "-"}",
            color = TextMuted,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatPrice(card.prices?.market),
                color = PriceGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(PriceGreen.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            )
            Text(
                text = card.rarity ?: "-",
                color = TextMuted,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(SurfaceLight)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
fun CardItemPreview() {
    CardItem(
        card = PokemonCard(
            name = "Blabla BLA - BLA29",
            setName = "BLA Promos",
            cardNumber = "BLA29",
            rarity = "Promo",
            prices = Prices(market = 21.78)
        ),
        onClick = {},
        modifier = Modifier.width(180.dp)
    )
}