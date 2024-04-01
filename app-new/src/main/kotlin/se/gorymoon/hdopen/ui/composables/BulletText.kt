package se.gorymoon.hdopen.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

const val bullet = "\u2022"

@Composable
fun BulletText(text: String, modifier: Modifier = Modifier){
    Text(buildAnnotatedString {
        withStyle(ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))) {
            append(bullet)
            append("\t\t")
            append(text)
        }
    }, modifier)
}