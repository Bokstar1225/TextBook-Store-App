package com.example.textbookapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StorefrontAdapter(private val storeItems: List<Book>) : RecyclerView.Adapter<StorefrontAdapter.StoreViewHolder>(){
    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImage: ImageView = itemView.findViewById(R.id.iv_cart_book_cover)
        val titleText: TextView = itemView.findViewById(R.id.tv_cart_book_title)
        val descriptionText: TextView = itemView.findViewById(R.id.tv_cart_book_description)
        val priceText: TextView = itemView.findViewById(R.id.tv_book_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val book = storeItems[position]
        holder.coverImage.setImageResource(book.imageResId)
        holder.titleText.text = book.title
        holder.priceText.text = book.price
    }

    override fun getItemCount(): Int = storeItems.size
}