package com.example.template.utils

fun String.isValidPrice(): Boolean {
    return this.matches(Regex("[0-9]+([,.][0-9]{1,2})?"))
}

fun String.isValidDate(): Boolean {
    return this.matches(Regex("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[\\/]\\d{4}\$"))
}