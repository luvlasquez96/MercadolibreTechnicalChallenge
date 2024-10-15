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

object Layout {

    object Spacing {

        object Small {
            val XXs = 2.dp
            val Xs = 4.dp
            val S = 8.dp
            val M = 12.dp
            val L = 16.dp
        }

        object Medium {
            val S = 24.dp
            val M = 32.dp
            val L = 50.dp
            val XL = 56.dp

        }

        object Large {
            val Xs = 64.dp
            val S = 68.dp
            val M = 80.dp
            val L = 120.dp

            val XL = 150.dp

            val XXl = 180.dp
            val XXXl = 250.dp
        }

        object Grid {
            val Margin = 24.dp
            val Gutter = 12.dp
        }

        object Size {
            val Icon = 1080.dp
            val Box = 100.dp
        }
    }
}

@Preview
@Composable
private fun LayoutSpacingSmallPreview() {
    Column {
        CustomSpacer(size = Layout.Spacing.Small.Xs)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Small.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Small.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Small.L)
    }
}

@Preview
@Composable
private fun LayoutSpacingMediumPreview() {
    Column {
        CustomSpacer(size = Layout.Spacing.Medium.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Medium.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Medium.XL)
    }
}

@Preview
@Composable
private fun LayoutSpacingLargePreview() {
    Column {
        CustomSpacer(size = Layout.Spacing.Large.S)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Large.M)
        Spacer(modifier = Modifier.height(5.dp))
        CustomSpacer(size = Layout.Spacing.Large.L)
    }
}

@Preview
@Composable
private fun LayoutSpacingGridPreview() {
    Column {
        repeat(5) {
            CustomSpacer(size = Layout.Spacing.Grid.Margin)
            Spacer(modifier = Modifier.height(Layout.Spacing.Grid.Gutter))
        }
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
