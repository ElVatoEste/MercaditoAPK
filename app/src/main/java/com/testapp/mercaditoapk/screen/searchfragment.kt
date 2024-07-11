package com.example.yourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testapp.mercaditoapk.R

class searchfragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val bottomNavHelper = BottomNavHelper(requireActivity())
        bottomNavHelper.setupBottomNav(bottomNavigationView)

        return view
    }
}
