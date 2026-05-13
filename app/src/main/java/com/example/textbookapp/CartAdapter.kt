package com.example.textbookapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<Book>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImage: ImageView = itemView.findViewById(R.id.iv_cart_book_cover)
        val titleText: TextView = itemView.findViewById(R.id.tv_cart_book_title)
        val descriptionText: TextView = itemView.findViewById(R.id.tv_cart_book_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val book = cartItems[position]
        holder.titleText.text = book.title
        holder.descriptionText.text = book.description
        holder.coverImage.setImageResource(book.imageResId)
    }

    override fun getItemCount(): Int = cartItems.size
}
