package com.themehedi.aifitnesscoach.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Composable
fun FeedbackOverlay(
    feedback: String,
    isCorrectForm: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Feedback card
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
        ) {
            Text(
                text = feedback,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // Correct form indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(64.dp)
                .clip(CircleShape)
                .background(if (isCorrectForm) Color.Green else Color.Red)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isCorrectForm) Icons.Default.Check else Icons.Default.Warning,
                contentDescription = "Form status",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}