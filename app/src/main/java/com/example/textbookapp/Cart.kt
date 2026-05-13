package com.example.textbookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Cart : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Book>>(mutableListOf())
    val cartItems: LiveData<MutableList<Book>> get() = _cartItems

    // NEW: Specific event for when an item is added
    private val _itemAddedEvent = MutableLiveData<Book?>()
    val itemAddedEvent: LiveData<Book?> get() = _itemAddedEvent

    fun addToCart(book: Book) {
        val currentList = _cartItems.value ?: mutableListOf()
        currentList.add(book)
        _cartItems.value = currentList
        
        // Trigger the event
        _itemAddedEvent.value = book
    }

    // Call this after showing the message to "consume" the event
    fun consumeItemAddedEvent() {
        _itemAddedEvent.value = null
    }

    fun getCartItemCount(): Int {
        return _cartItems.value?.size ?: 0
    }
}