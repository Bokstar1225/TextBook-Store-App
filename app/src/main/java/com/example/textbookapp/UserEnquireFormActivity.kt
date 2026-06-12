package com.example.textbookapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UserEnquireFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_enquire_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilMessage = findViewById<TextInputLayout>(R.id.tilMessage)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etMessage = findViewById<TextInputEditText>(R.id.etMessage)

        val btnEnquire = findViewById<Button>(R.id.btnEnquire)
        btnEnquire.setOnClickListener {
            validateInput(tilName, etName, tilEmail, etEmail, tilMessage, etMessage)
        }
    }

    private fun validateInput(
        tilName: TextInputLayout, etName: TextInputEditText,
        tilEmail: TextInputLayout, etEmail: TextInputEditText,
        tilMessage: TextInputLayout, etMessage: TextInputEditText
    ) {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val message = etMessage.text.toString().trim()

        var isValid = true

        // Name validation
        if (name.isEmpty()) {
            tilName.error = "Name is required"
            isValid = false

        }else{
            tilName.error = null
        }

        if(email.isEmpty()){
            tilEmail.error = "Email is required"
            isValid = false

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.error = "Enter a valid email address"
            isValid = false

        }else{
            tilEmail.error = null
        }

        if(message.isEmpty()){
            tilMessage.error = "Message is required"
            isValid = false

        }else{
            tilMessage.error = null
        }

        if(isValid){
            // Proceed with registration logic
            Toast.makeText(this, "Enquire sent to seller", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, UserMainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}