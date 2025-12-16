package com.example.noteappcleanarchitecture.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noteappcleanarchitecture.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SplashScreen() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        val isLandscape = maxWidth > maxHeight

        val logoSize = if (isLandscape) {
            (maxHeight * 0.6f).coerceIn(140.dp, 260.dp)
        } else {
            (maxWidth * 0.65f).coerceIn(180.dp, 320.dp)
        }

        Image(
            painter = painterResource(R.drawable.note_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(logoSize)
        )
    }
}