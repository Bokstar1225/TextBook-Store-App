package com.example.textbookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class UserEnquireFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_enquire, container, false)

        val enquireButton: Button = view.findViewById(R.id.enquireButton)
        enquireButton.setOnClickListener {
            Toast.makeText(requireContext(), "Enquiry sent to seller!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}