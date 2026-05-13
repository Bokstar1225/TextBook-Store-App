package com.example.textbookapp

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class UserMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val homeBtn: LinearLayout = findViewById(R.id.ll_home)
        val enquireBtn: LinearLayout = findViewById(R.id.ll_enquire)
        val cartBtn: LinearLayout = findViewById(R.id.ll_cart)

        // Set default fragment
        if (savedInstanceState == null) {
            replaceFragment(UserHomeFragment())
        }

        homeBtn.setOnClickListener { replaceFragment(UserHomeFragment()) }
        enquireBtn.setOnClickListener { replaceFragment(UserEnquireFragment()) }
        cartBtn.setOnClickListener { replaceFragment(UserCartFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}