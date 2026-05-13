package com.example.textbookapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var bookList: List<Book>,
    private val onAddToCartClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImage: ImageView = itemView.findViewById(R.id.iv_book_cover)
        val titleText: TextView = itemView.findViewById(R.id.tv_book_title)
        val priceText: TextView = itemView.findViewById(R.id.tv_book_price)
        val addToCartButton: Button = itemView.findViewById(R.id.btn_add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.titleText.text = book.title
        holder.priceText.text = book.price
        holder.coverImage.setImageResource(book.imageResId)

        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(book)
            Toast.makeText(holder.itemView.context, "${book.title} added to cart!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun filter(query : String){
        bookList = if(query.isEmpty()){
            bookList.toMutableList()
        }else{
            bookList.filter { it.title.contains(query, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }
}