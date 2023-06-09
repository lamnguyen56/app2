package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.MediaController
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        setupWithNavController(bottomNavigationView, navController)



//        val homeFragment = homeFragment()
//        val addingFragment = addingFragment()
//        val profileFragment = profileFragment()
//
//        val fragmentContainer = findViewById<FrameLayout>(R.id.frame_layout)
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.frame_layout, homeFragment)
//            addToBackStack(null)
//            commit()
//        }
//
////        val homeButton = findViewById<MenuItem>(R.id.myHome)
////        homeButton.setOnMenuItemClickListener {
////            val fragment = homeFragment()
////            supportFragmentManager.beginTransaction()
////                .replace(R.id.frame_layout, fragment)
////                .commit()
////            true
////        }
//
//        val addingButton = findViewById<Button>(R.id.fab)
//        addingButton.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.frame_layout, addingFragment)
//                addToBackStack(null)
//                commit()
//            }
//        }
//
//        val myMenu = findViewById<Toolbar>(R.id.bottomNavigationView)
//        setSupportActionBar(myMenu)
//
//        myMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.myHome -> {
//                    // Chuyển đổi sang HomeFragment
//                    val fragment = homeFragment()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, fragment, "homeFragment")
//                        .commit()
//                    true
//                }
//                R.id.myProfile -> {
//                    // Chuyển đổi sang ProfileFragment
//                    val fragment = profileFragment()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, fragment, "profileFragment")
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }

//        val profileButton = findViewById<MenuItem>(R.id.myProfile)
//        profileButton.setOnMenuItemClickListener {
//            val fragment = profileFragment()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frame_layout, fragment)
//                .commit()
//            true
//        }


    }
}