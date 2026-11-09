package com.example.travel_planner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travel_planner.R

/**
 * WelcomeScreen:
 * - Full-screen background image with a dark
semi-transparent overlay
 * - Center: Travel Planner branding with paper plane icon
 * - Bottom: "Get Started" button and clickable "Log in"
text link
 */
@Composable
fun WelcomeScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 1. Full-screen background image
        Image(
            painter = painterResource(id =
                R.drawable.welcome_bg),
            contentDescription = "Travel background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Dark semi-transparent overlay for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha =
                    0.45f))
        )

        // 3. Main content respecting system insets (status bar & nav bar)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 28.dp, vertical =
                    24.dp),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            // Vertically centered header branding
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {
                // Paper plane icon
                Icon(
                    imageVector =
                        Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Paper Plane Icon",
                            tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Large title
                Text(
                    text = "Travel Planner",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle
                Text(
                    text = "Plan · Book · Explore",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha =
                        0.85f),
                    letterSpacing = 1.5.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Bottom action section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {
                // Wide rounded white button
                Button(
                    onClick = onNavigateToRegister,
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF1E293B)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // "Already have an account? Log in" clickable text
                val loginText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color =
                        Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp)) {
                        append("Already have an account? ")
                    }
                    pushStringAnnotation(tag = "LOGIN",
                        annotation = "login")
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    ) {
                        append("Log in")
                    }
                    pop()
                }

                ClickableText(
                    text = loginText,
                    onClick = { offset ->
                        loginText.getStringAnnotations(tag
                        = "LOGIN", start = offset, end =
                            offset)
                            .firstOrNull()?.let {
                                onNavigateToLogin()
                            }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}