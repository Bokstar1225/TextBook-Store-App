package com.example.textbookapp

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class SellerHomeFragment : Fragment() {
    private lateinit var store: Storefront
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seller_home, container, false)

        store = ViewModelProvider(requireActivity())[Storefront::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_books)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val books = listOf(
            Book(1, "Computer Science Course Companion", "R2100", R.drawable.computer_science_textbook),
            Book(2, "Philosophy A Complete Introduction", "R1000", R.drawable.philosophy_textbook),
            Book(3, "Engineering Fundamentals", "R1700", R.drawable.engineering_textbook),
            Book(4, "A Textbook of Physics", "R2000", R.drawable.physics_textbook)
        )

        val adapter = BookAdapter(books, buttonText = "Add to Storefront") { selectedBook ->
            store.addToStore(selectedBook)
            Toast.makeText(requireContext(), "${selectedBook.title} added to storefront!", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        val searchBar : TextInputEditText = view.findViewById(R.id.et_search)

        searchBar.addTextChangedListener(object : android.text.TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        return view
    }
}