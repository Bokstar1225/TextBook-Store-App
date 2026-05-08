package com.example.textbookapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buyerButton = findViewById<Button>(R.id.button)
        val sellerButton = findViewById<Button>(R.id.button2)

        buyerButton.setOnClickListener {
            // Handle buyer button click
            val intent = Intent(this, UserSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        sellerButton.setOnClickListener {
            // Handle seller button click
            val intent = Intent(this, SellerSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}