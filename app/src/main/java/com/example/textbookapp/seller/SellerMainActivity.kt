package com.example.textbookapp.seller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.textbookapp.R
import com.example.textbookapp.seller.SellerStorefrontFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SellerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seller_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        if(savedInstanceState == null){
            replaceFragment(SellerHomeFragment())
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(SellerHomeFragment())
                    true
                }
                R.id.navigation_enquire -> {
                    replaceFragment(SellerEnquireFragment())
                    true
                }
                R.id.navigation_cart -> {
                    replaceFragment(SellerCartFragment())
                    true
                }
                R.id.navigation_storefront -> {
                    replaceFragment(SellerStorefrontFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}