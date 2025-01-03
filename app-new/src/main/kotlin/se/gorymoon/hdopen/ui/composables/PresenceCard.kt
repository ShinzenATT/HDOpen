package se.gorymoon.hdopen.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import se.gorymoon.hdopen.dto.NowPlayingData
import se.gorymoon.hdopen.dto.PresenceData
import se.gorymoon.hdopen.ui.models.NowPlayingState
import se.gorymoon.hdopen.ui.models.PresenceState
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun PresenceCard(modifier: Modifier = Modifier, state: MutableState<PresenceData> = PresenceState, startExpanded: Boolean = false, containerColor: Color = MaterialTheme.colorScheme.surfaceVariant, textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant) {
    val sales by remember { state }
    var isExpanded by remember{ mutableStateOf(startExpanded) }

    Card(modifier = modifier.fillMaxWidth().clickable { isExpanded = !isExpanded }, colors = CardDefaults.cardColors(containerColor = containerColor, contentColor = textColor)) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = "${sales.totalPeople} purchases in the last hour",
                fontSize = 5.em,
                maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium,
                textAlign = if(isExpanded) TextAlign.Left else TextAlign.Center,
                modifier = if(isExpanded) Modifier.fillMaxWidth().padding(bottom = 5.dp) else Modifier.fillMaxWidth(),
            )
            if(isExpanded) {
                if(sales.activePeople > 0)
                    Text("${sales.activePeople} active people", fontSize = 4.em, modifier = Modifier.padding(bottom = 5.dp))
                if(sales.inactivePeople > 0)
                    Text("${sales.inactivePeople} inactive people", fontSize = 4.em, modifier = Modifier.padding(bottom = 5.dp))
                if(sales.sittande > 0)
                    Text("${sales.sittande} sittande", fontSize = 4.em, modifier = Modifier.padding(bottom = 5.dp))
                DurationText(sales.timeSinceBuy, 4.em) { "$it since last buy"}
            }

        }
    }
}

@Composable
@Preview
fun PresenceCardPreview() {
    MaterialTheme {
        PresenceCard(state = mutableStateOf(PresenceData(5,3,1,1, 32.toDuration(DurationUnit.MINUTES))))
    }
}

@Composable
@Preview
fun PresenceCardExpandedPreview() {
    MaterialTheme {
        PresenceCard(state = mutableStateOf(PresenceData(5,3,1,1, 32.toDuration(DurationUnit.MINUTES))), startExpanded =  true)
    }
}
