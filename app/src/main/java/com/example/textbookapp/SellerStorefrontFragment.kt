package com.example.textbookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SellerStorefrontFragment : Fragment() {

    private lateinit var storefront: Storefront

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seller_storefront, container, false)
        storefront = ViewModelProvider(requireActivity())[Storefront::class.java]

        // Section 1: Books Your Selling
        val rvSelling: RecyclerView = view.findViewById(R.id.rv_selling_items)
        rvSelling.layoutManager = GridLayoutManager(requireContext(), 2)
        
        storefront.storeItems.observe(viewLifecycleOwner) { items ->
            // Use BookAdapter but hide the button as per the UI image
            rvSelling.adapter = BookAdapter(items, showButton = false)
        }

        // Section 2: Books You Have Sold
        val rvSold: RecyclerView = view.findViewById(R.id.rv_sold_items)
        rvSold.layoutManager = GridLayoutManager(requireContext(), 2)

        storefront.soldItems.observe(viewLifecycleOwner) { items ->
            rvSold.adapter = BookAdapter(items, showButton = false)
        }

        // Initial setup for Sold items (Demo data to match image)
        if (storefront.soldItems.value.isNullOrEmpty()) {
             val demoSold = listOf(
                Book(3, "Engineering Fundamentals", "R1700", R.drawable.engineering_textbook),
                Book(4, "A Textbook of Physics", "R2000", R.drawable.physics_textbook)
            )
            demoSold.forEach { storefront.soldItems.value?.add(it) }
        }

        return view
    }
}