package com.example.textbookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Storefront : ViewModel() {
    private val _storeItems = MutableLiveData<MutableList<Book>>(mutableListOf())
    val storeItems: LiveData<MutableList<Book>> get() = _storeItems

    private val _soldItems = MutableLiveData<MutableList<Book>>(mutableListOf())
    val soldItems: LiveData<MutableList<Book>> get() = _soldItems

    fun addToStore(book: Book) {
        val currentList = _storeItems.value ?: mutableListOf()
        if (!currentList.contains(book)) {
            currentList.add(book)
            _storeItems.value = currentList
        }
    }

    fun markAsSold(book: Book) {
        val currentStore = _storeItems.value ?: mutableListOf()
        if (currentStore.remove(book)) {
            _storeItems.value = currentStore
            val currentSold = _soldItems.value ?: mutableListOf()
            currentSold.add(book)
            _soldItems.value = currentSold
        }
    }

    fun getStoreItemCount(): Int = _storeItems.value?.size ?: 0
}