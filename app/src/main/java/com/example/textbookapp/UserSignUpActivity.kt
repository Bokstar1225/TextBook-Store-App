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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UserSignUpActivity : AppCompatActivity() {

    private lateinit var auth : Authentication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_sign_up)

        // Initialize Authentication ViewModel
        auth = ViewModelProvider(this)[Authentication::class.java]
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            validateInput(tilName, etName, tilEmail, etEmail, tilPassword, etPassword)
        }
    }
    private fun validateInput(
        tilName: TextInputLayout, etName: TextInputEditText,
        tilEmail: TextInputLayout, etEmail: TextInputEditText,
        tilPassword: TextInputLayout, etPassword: TextInputEditText
    ) {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        var isValid = true

        // Name validation
        if (name.isEmpty()) {
            tilName.error = "Name is required"
            isValid = false
        } else {
            tilName.error = null
        }

        // Email validation
        if (email.isEmpty()) {
            tilEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Enter a valid email address"
            isValid = false
        } else {
            tilEmail.error = null
        }

        // Password validation
        if (password.isEmpty()) {
            tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            tilPassword.error = null
        }

        if (isValid) {
            // Proceed with registration logic
            if(auth.signUp(email, password)){
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
