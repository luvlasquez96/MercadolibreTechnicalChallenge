package com.example.mercadolibremobiletest.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun String.toHttpsUrl(): String = this.replace("http://", "https://")
fun Double.formatPrice(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
    currencyFormat.isDecimalSeparatorAlwaysShown = false
    currencyFormat.maximumFractionDigits = 0
    return currencyFormat.format(this)
}