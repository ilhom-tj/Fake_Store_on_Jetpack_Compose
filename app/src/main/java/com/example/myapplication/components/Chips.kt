package com.example.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    startIcon: () -> ImageVector? = { null },
    isStartIconEnabled: Boolean = false,
    isSelected : Boolean = false,
    startIconTint: Color = Color.Unspecified,
    onStartIconClicked: () -> Unit = { },
    endIcon: () -> ImageVector? = { null },
    index : Int,
    isEndIconEnabled: Boolean = false,
    endIconTint: Color = Color.Unspecified,
    onEndIconClicked: () -> Unit = { },
    color: Color = Color(0xFFF7F7F7), // MaterialTheme.colors.surface,
    contentDescription: String,
    label: String,
    isClickable: Boolean = false,
    onClick: () -> Unit = { }
) {
    var margins = PaddingValues(start = 8.dp)
    if (index == 0){
        margins = PaddingValues(start = 16.dp)
    }else{
        margins = PaddingValues(start = 8.dp)
    }

    if (isSelected){
        Surface(
            modifier = Modifier.padding(margins).clickable(
                enabled = isClickable,
                onClick = { onClick() }
            ),
            shape = RoundedCornerShape(13.dp),
            color = Color(0xFFF5E8D1)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val leader = startIcon()
                val trailer = endIcon()
                if (leader != null) {
                    Icon(
                        leader,
                        contentDescription = contentDescription,
                        tint = startIconTint,
                        modifier = Modifier
                            .clickable(enabled = isStartIconEnabled, onClick = onStartIconClicked)
                            .padding(horizontal = 4.dp)
                    )
                }
                Text(
                    label,
                    modifier = Modifier.padding(start = 16.dp,end = 16.dp,top = 10.dp,bottom = 10.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button.copy(color = Color(0xFFE49346))
                )
                if (trailer != null) {
                    Icon(
                        trailer,
                        contentDescription = contentDescription,
                        tint = endIconTint,
                        modifier = Modifier
                            .clickable(enabled = isEndIconEnabled, onClick = onEndIconClicked)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }

    }else{
        Surface(
            modifier = Modifier.padding(margins).clickable(
                enabled = isClickable,
                onClick = { onClick() }
            ),
            shape = RoundedCornerShape(13.dp),
            color = color
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val leader = startIcon()
                val trailer = endIcon()
                if (leader != null) {
                    Icon(
                        leader,
                        contentDescription = contentDescription,
                        tint = startIconTint,
                        modifier = Modifier
                            .clickable(enabled = isStartIconEnabled, onClick = onStartIconClicked)
                            .padding(horizontal = 4.dp)
                    )
                }
                Text(
                    label,
                    modifier = Modifier.padding(start = 16.dp,end = 16.dp,top = 10.dp,bottom = 10.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button.copy(color = Color(0xFF9B9DA1))
                )
                if (trailer != null) {
                    Icon(
                        trailer,
                        contentDescription = contentDescription,
                        tint = endIconTint,
                        modifier = Modifier
                            .clickable(enabled = isEndIconEnabled, onClick = onEndIconClicked)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }

}
