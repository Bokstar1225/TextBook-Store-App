package com.example.textbookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SellerCartFragment : Fragment() {

    private lateinit var cart: Cart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seller_cart, container, false)
        cart = ViewModelProvider(requireActivity())[Cart::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_cart_items)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        cart.cartItems.observe(viewLifecycleOwner) { items ->
            // Use the specific CartAdapter for the horizontal layout
            val adapter = CartAdapter(items)
            recyclerView.adapter = adapter
        }

        val checkoutButton: Button = view.findViewById(R.id.btn_checkout)
        checkoutButton.setOnClickListener {
            if (cart.getCartItemCount() > 0) {
                Toast.makeText(requireContext(), "Proceeding to checkout...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Cart is empty", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}