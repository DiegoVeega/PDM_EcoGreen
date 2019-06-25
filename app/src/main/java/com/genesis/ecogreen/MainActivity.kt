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
         navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        setUpBottomNavMenu(navController)
        setUpActionBar(navController)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                bottom_navigation?.visibility=View.GONE
            } else {
                bottom_navigation?.visibility=View.VISIBLE
            }

            if(R.id.projectsFragment==nd.id || R.id.tasksFragment==nd.id || R.id.userFragment==nd.id){
                supportActionBar?.setHomeButtonEnabled(false)
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }else{
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this,R.id.myNavHostFragment)
        return navController.navigateUp()
    }

     private fun setUpBottomNavMenu(navController: NavController){
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpActionBar(navController: NavController){
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}
