package com.example.textbookapp

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class SellerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seller_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sellerHomeBtn: LinearLayout = findViewById(R.id.ll_home)
        val sellerEnquireBtn: LinearLayout = findViewById(R.id.ll_enquire)
        val sellerCartBtn: LinearLayout = findViewById(R.id.ll_cart)
        val sellerStorefrontBtn: LinearLayout = findViewById(R.id.ll_storefront)

        if(savedInstanceState == null){
            replaceFragment(SellerHomeFragment())
        }

        sellerHomeBtn.setOnClickListener { replaceFragment(SellerHomeFragment()) }
        sellerEnquireBtn.setOnClickListener { replaceFragment(SellerEnquireFragment()) }
        sellerCartBtn.setOnClickListener { replaceFragment(SellerCartFragment()) }
        sellerStorefrontBtn.setOnClickListener { replaceFragment(SellerStorefrontFragment()) }
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}