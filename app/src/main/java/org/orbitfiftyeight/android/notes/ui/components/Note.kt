package org.orbitfiftyeight.android.notes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.orbitfiftyeight.android.notes.theme.green

@Composable
fun Note() {
    val bgShape: Shape = RoundedCornerShape(4.dp)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(1.dp, bgShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, bgShape)
    ) {
        NoteColor(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp, end = 16.dp),
            color = green,
            size = 40.dp,
            border = 2.dp
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Title",
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = "Content",
                color = Color.Black.copy(alpha = 0.75f),
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                )
            )
        }
        Checkbox(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun NoteColor(
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp,
    border: Dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
            .border(
                BorderStroke(
                    border, SolidColor(Color.Black)
                ), CircleShape
            )
    )
}

@Preview
@Composable
private fun NotePreview() {
    Note()
}

@Preview
@Composable
private fun NoteColorPreview() {
    NoteColor(
        color = Color.Red,
        size = 40.dp,
        border = 2.dp
    )
}