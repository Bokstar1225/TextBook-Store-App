package com.example.textbookapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private val fullBookList: List<Book>,
    private val buttonText: String = "Add to Cart",
    private val showButton: Boolean = true,
    private val onAddToCartClick: (Book) -> Unit = {}
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var displayedBookList: List<Book> = fullBookList

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
        val book = displayedBookList[position]
        holder.titleText.text = book.title
        holder.priceText.text = book.price
        holder.coverImage.setImageResource(book.imageResId)
        
        if (showButton) {
            holder.addToCartButton.visibility = View.VISIBLE
            holder.addToCartButton.text = buttonText
            holder.addToCartButton.setOnClickListener {
                onAddToCartClick(book)
            }
        } else {
            holder.addToCartButton.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return displayedBookList.size
    }

    fun filter(query: String) {
        displayedBookList = if (query.isEmpty()) {
            fullBookList
        } else {
            fullBookList.filter { it.title.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
