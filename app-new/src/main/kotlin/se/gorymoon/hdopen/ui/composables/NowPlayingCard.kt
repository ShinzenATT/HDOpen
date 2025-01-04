package se.gorymoon.hdopen.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import se.gorymoon.hdopen.dto.NowPlayingData
import se.gorymoon.hdopen.ui.models.NowPlayingState
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun NowPlayingCard(modifier: Modifier = Modifier, state: MutableState<NowPlayingData> = NowPlayingState, startExpanded: Boolean = false, containerColor: Color = MaterialTheme.colorScheme.surfaceVariant, textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant) {
    val media by remember { state }
    var isExpanded by remember{ mutableStateOf(startExpanded) }

    Card(modifier = modifier.fillMaxWidth().clickable { isExpanded = !isExpanded }, colors = CardDefaults.cardColors(containerColor = containerColor, contentColor = textColor)) {
        Column(Modifier.padding(10.dp)) {
            if (media.song == null) {
                Text(
                    text = "Nothing is playing",
                    fontSize = 5.em,
                    fontWeight = FontWeight.Medium,
                    textAlign = if(isExpanded) TextAlign.Left else TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = "Playing: ${media.song}",
                    fontSize = 5.em,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = if(isExpanded) TextAlign.Justify else TextAlign.Center,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    modifier = if(isExpanded) Modifier.fillMaxWidth().padding(bottom = 5.dp) else Modifier.fillMaxWidth()
                )
            }
            if(isExpanded) {
                if (!media.artist.isNullOrEmpty()) {
                    Text(text = "Artist: ${media.artist}", fontSize = 4.em, modifier = Modifier.padding(bottom = 5.dp))
                }
                DurationText(media.duration, fontSize = 4.em) { "Playing for $it" }
            }
        }
    }
}

@Composable
@Preview
fun NowPlayingCardPreview() {
    MaterialTheme {
        NowPlayingCard(state = mutableStateOf(NowPlayingData("Song title lorem ipsum dolar amet soundtrack", "Song Artist", 4.toDuration(DurationUnit.HOURS))))
    }
}

@Composable
@Preview
fun NowPlayingCardExpandedPreview() {
    MaterialTheme {
        NowPlayingCard(state = mutableStateOf(NowPlayingData("Song title lorem ipsum dolar amet soundtrack", "Song Artist", 4.toDuration(DurationUnit.HOURS))), startExpanded =  true)
    }
}
