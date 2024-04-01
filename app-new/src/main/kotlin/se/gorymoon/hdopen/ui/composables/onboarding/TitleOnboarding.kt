package se.gorymoon.hdopen.ui.composables.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import se.gorymoon.hdopen.R
import se.gorymoon.hdopen.ui.theme.HDOpenTheme

@Composable
fun TitleOnboarding() = Column(verticalArrangement = Arrangement.Center) {
    AsyncImage(R.drawable.hd_logo, contentDescription = "Logo", modifier = Modifier.fillMaxWidth(), alignment = Alignment.Center)
    Text(
        "Welcome to HDOpen!",
        textAlign = TextAlign.Center,
        fontSize = 5.em,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        "It has seen numerous upgrades since your last visit! Now with Material 3, custom colors, events and additional Chassit data.",
        textAlign = TextAlign.Justify,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun  TitlePreview() = HDOpenTheme {
    Surface {
        TitleOnboarding()
    }
}