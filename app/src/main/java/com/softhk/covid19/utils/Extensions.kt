package com.softhk.covid19.utils

fun String.setValueWhenIsEmpty() = if (this.length <= 0) "0" else this
