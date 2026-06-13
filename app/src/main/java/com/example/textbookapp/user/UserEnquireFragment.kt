package com.example.textbookapp.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.textbookapp.R

class UserEnquireFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_enquire, container, false)

        val enquireButton: Button = view.findViewById(R.id.enquireButton)
        enquireButton.setOnClickListener {
            val intent = Intent(requireContext(), UserEnquireFormActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}