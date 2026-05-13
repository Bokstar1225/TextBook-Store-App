package com.example.textbookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class SellerEnquireFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seller_enquire, container, false)

        val enquireButton: Button = view.findViewById(R.id.enquireButton)

        enquireButton.setOnClickListener {
            Toast.makeText(requireContext(), "Enquiry sent to buyer!", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}