package com.example.mercadolibremobiletest.utils

fun String.toHttpsUrl():String = this.replace("http://", "https://")