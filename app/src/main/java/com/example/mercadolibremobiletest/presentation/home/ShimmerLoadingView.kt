package com.example.mercadolibremobiletest.presentation.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = -1.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing)
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(Color.LightGray, Color.White, Color.LightGray),
        start = Offset(translateAnim.value * 300f, 0f),
        end = Offset(translateAnim.value * -300f, 0f)
    )

    Box(
        modifier = modifier
            .background(brush)
    )
}

@Composable
fun ShimmerLoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(5) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Layout.Spacing.Small.S, horizontal = Layout.Spacing.Small.L)
                    .height(Layout.Spacing.Large.M)
                    .padding(Padding.Small.S),
                shape = RoundedCornerShape(Layout.Spacing.Small.S)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(Layout.Spacing.Large.Xs)
                    ) {
                        ShimmerEffect(modifier = Modifier.fillMaxSize())
                    }
                    Spacer(modifier = Modifier.width(Layout.Spacing.Small.L))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f)
                            .padding(Padding.Small.S),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(Layout.Spacing.Small.L)
                        )
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(Layout.Spacing.Small.L)
                        )
                    }
                }
            }
        }
    }
}