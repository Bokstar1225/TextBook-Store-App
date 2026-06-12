package com.example.textbookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton

class BookDetailFragment : Fragment() {

    private lateinit var cart: Cart
    private lateinit var storefront: Storefront
    private var bookId: Int = -1
    private lateinit var bookTitle: String
    private lateinit var bookPrice: String
    private var bookImageResId: Int = -1
    private lateinit var bookDescription: String
    private var isSeller: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getInt(ARG_BOOK_ID)
            bookTitle = it.getString(ARG_BOOK_TITLE) ?: ""
            bookPrice = it.getString(ARG_BOOK_PRICE) ?: ""
            bookImageResId = it.getInt(ARG_BOOK_IMAGE)
            bookDescription = it.getString(ARG_BOOK_DESCRIPTION) ?: ""
            isSeller = it.getBoolean(ARG_IS_SELLER, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_detail, container, false)
        
        val backButton: ImageView = view.findViewById(R.id.iv_detail_back)
        val coverImage: ImageView = view.findViewById(R.id.iv_detail_book_cover)
        val titleText: TextView = view.findViewById(R.id.tv_detail_book_title)
        val priceText: TextView = view.findViewById(R.id.tv_detail_book_price)
        val descriptionText: TextView = view.findViewById(R.id.tv_detail_book_description)
        val actionButton: MaterialButton = view.findViewById(R.id.btn_detail_add_to_cart)

        titleText.text = bookTitle
        priceText.text = bookPrice
        descriptionText.text = bookDescription
        coverImage.setImageResource(bookImageResId)

        if (isSeller) {
            storefront = ViewModelProvider(requireActivity())[Storefront::class.java]
            actionButton.text = "Add to Storefront"
            actionButton.setOnClickListener {
                val book = Book(bookId, bookTitle, bookPrice, bookImageResId, bookDescription)
                storefront.addToStore(book)
                Toast.makeText(requireContext(), "$bookTitle added to storefront!", Toast.LENGTH_SHORT).show()
            }
        } else {
            cart = ViewModelProvider(requireActivity())[Cart::class.java]
            actionButton.text = "Add to Cart"
            actionButton.setOnClickListener {
                val book = Book(bookId, bookTitle, bookPrice, bookImageResId, bookDescription)
                cart.addToCart(book)
                Toast.makeText(requireContext(), "$bookTitle added to cart!", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        private const val ARG_BOOK_ID = "book_id"
        private const val ARG_BOOK_TITLE = "book_title"
        private const val ARG_BOOK_PRICE = "book_price"
        private const val ARG_BOOK_IMAGE = "book_image"
        private const val ARG_BOOK_DESCRIPTION = "book_description"
        private const val ARG_IS_SELLER = "is_seller"

        @JvmStatic
        fun newInstance(book: Book, isSeller: Boolean = false) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BOOK_ID, book.id)
                    putString(ARG_BOOK_TITLE, book.title)
                    putString(ARG_BOOK_PRICE, book.price)
                    putInt(ARG_BOOK_IMAGE, book.imageResId)
                    putString(ARG_BOOK_DESCRIPTION, book.description)
                    putBoolean(ARG_IS_SELLER, isSeller)
                }
            }
    }
}