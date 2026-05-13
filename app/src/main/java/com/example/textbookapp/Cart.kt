package com.example.textbookapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Cart : ViewModel() {
    // The private mutable list that holds the actual data
    private val _cartItems = MutableLiveData<MutableList<Book>>(mutableListOf())

    // The public immutable LiveData that the Activity will observe
    val cartItems: LiveData<MutableList<Book>> get() = _cartItems

    fun addToCart(book: Book) {
        // Get the current list, add the new book, and update the LiveData
        val currentList = _cartItems.value ?: mutableListOf()
        currentList.add(book)
        _cartItems.value = currentList
    }

    fun getCartItemCount(): Int {
        return _cartItems.value?.size ?: 0
    }
}