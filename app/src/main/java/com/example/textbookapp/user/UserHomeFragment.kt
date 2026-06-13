package com.example.textbookapp.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textbookapp.BookDetailFragment
import com.example.textbookapp.R
import com.example.textbookapp.adapters.BookAdapter
import com.example.textbookapp.data.Book
import com.example.textbookapp.data.Cart
import com.google.android.material.textfield.TextInputEditText

class UserHomeFragment : Fragment() {
    private lateinit var cart: Cart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_home, container, false)

        cart = ViewModelProvider(requireActivity())[Cart::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_books)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val books = listOf(
            Book(
                1,
                "Computer Science Course Companion",
                "R2100",
                R.drawable.computer_science_textbook
            ),
            Book(2, "Philosophy A Complete Introduction", "R1000", R.drawable.philosophy_textbook),
            Book(3, "Engineering Fundamentals", "R1700", R.drawable.engineering_textbook),
            Book(4, "A Textbook of Physics", "R2000", R.drawable.physics_textbook)
        )

        val adapter = BookAdapter(
            fullBookList = books,
            onBookClick = { selectedBook ->
                val detailFragment = BookDetailFragment.Companion.newInstance(selectedBook)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            onAddToCartClick = { selectedBook ->
                cart.addToCart(selectedBook)
            }
        )
        recyclerView.adapter = adapter

        cart.itemAddedEvent.observe(viewLifecycleOwner) { book ->
            book?.let {
                Toast.makeText(requireContext(), "${it.title} added to cart!", Toast.LENGTH_SHORT).show()
                cart.consumeItemAddedEvent()
            }
        }

        val searchBar : TextInputEditText = view.findViewById(R.id.et_search)

        searchBar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        return view
    }
}