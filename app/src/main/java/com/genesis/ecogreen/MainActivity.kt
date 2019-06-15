package com.genesis.ecogreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.genesis.ecogreen.Fragments.projectsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val nav=bottom_navigation
         navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        setUpBottomNavMenu(navController)
        setUpSideNavigationMenu(navController)
        setUpActionBar(navController)
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                bottom_navigation?.visibility=View.GONE
                navigation_drawer?.visibility=View.GONE
                drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                bottom_navigation?.visibility=View.VISIBLE
                navigation_drawer?.visibility=View.VISIBLE
                drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.myNavHostFragment), drawer)
    }

     private fun setUpBottomNavMenu(navController: NavController){
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

     private fun setUpSideNavigationMenu(navController: NavController){
        navigation_drawer?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpActionBar(navController: NavController){
        NavigationUI.setupActionBarWithNavController(this, navController, drawer)
    }
}
