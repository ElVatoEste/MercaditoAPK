package com.example.yourapp

import android.app.Activity

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testapp.mercaditoapk.R

class BottomNavHelper(private val activity: Activity) {

    fun setupBottomNav(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            resetIcons(bottomNavigationView)
            when (item.itemId) {
                R.id.nav_menu -> {
                    item.setIcon(R.drawable.menubl)
                    // Replace with ProfileFragment or activity
                }
                R.id.nav_search -> {
                    item.setIcon(R.drawable.searchbl)
                    // Replace with MailFragment or activity
                }
                R.id.nav_add -> {
                    item.setIcon(R.drawable.addbl)
                    // Replace with AddFragment or activity
                }
                R.id.nav_chat -> {
                    item.setIcon(R.drawable.chatbl)
                    // Replace with OffersFragment or activity
                }
                R.id.nav_settings -> {
                    item.setIcon(R.drawable.settingsbl)
                    // Replace with MoreFragment or activity
                }
            }
            true
        }
    }

    private fun resetIcons(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.menu.findItem(R.id.nav_menu).setIcon(R.drawable.menub)
        bottomNavigationView.menu.findItem(R.id.nav_search).setIcon(R.drawable.searchb)
        bottomNavigationView.menu.findItem(R.id.nav_add).setIcon(R.drawable.addbl)
        bottomNavigationView.menu.findItem(R.id.nav_chat).setIcon(R.drawable.chatb)
        bottomNavigationView.menu.findItem(R.id.nav_settings).setIcon(R.drawable.settingsb)
    }
}
