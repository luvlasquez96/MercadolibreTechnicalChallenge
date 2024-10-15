package com.example.mercadolibretest.design_system.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Padding {

    object Small {
        val XXXs = 1.dp
        val XXs = 2.dp
        val Xs = 4.dp
        val S = 8.dp
        val M = 12.dp
        val L = 16.dp
    }

    object Medium {
        val S = 24.dp
        val M = 32.dp
        val L = 48.dp
    }

    object Large {
        val S = 64.dp
        val M = 88.dp
        val L = 120.dp
    }
}

@Preview
@Composable
private fun PaddingPreview() {
    Column {
        CustomSpacer(size = Padding.Small.Xs)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Small.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Small.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Small.L)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Medium.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Medium.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Medium.L)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Large.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Large.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Padding.Large.L)
    }
}

@Composable
private fun CustomSpacer(
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .height(size)
            .fillMaxWidth()
            .background(Color.Red),
    )
}
