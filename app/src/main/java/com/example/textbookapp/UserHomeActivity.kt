package com.example.textbookapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.rv_books)

        val homeIcon: ImageView = findViewById(R.id.imageView)
        val enquireIcon: ImageView = findViewById(R.id.iv_enquire)
        val cartIcon: ImageView = findViewById(R.id.iv_cart)

        // Set up RecyclerView

        // GridLayoutManger for the 2 columns of books
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // In-memory list of textbooks
        val books = listOf(
            Book(1, "Computer Science Course Companion", "R2100", R.drawable.computer_science_textbook),
            Book(2, "Philosophy A Complete Introduction", "R1000", R.drawable.philosophy_textbook),
            Book(3, "Engineering Fundamentals", "R1700", R.drawable.engineering_textbook),
            Book(4, "A Textbook of Physics", "R2000", R.drawable.physics_textbook)
        )

        //variable to hold the adapter contains book data and list
        val adapter = BookAdapter(books)
        recyclerView.adapter = adapter
    }
}