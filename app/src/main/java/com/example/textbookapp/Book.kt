package com.example.textbookapp

//Data class that holds the book information
data class Book(
    val id: Int,
    val title: String,
    val price: String,
    val imageResId: Int,
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
)
