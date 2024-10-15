package com.example.mercadolibremobiletest.utils

import java.text.NumberFormat
import java.util.Locale

fun String.toHttpsUrl():String = this.replace("http://", "https://")

fun Double.formatPrice(): String{return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)}
