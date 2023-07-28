package com.chaitanya.foodorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.chaitanya.foodorder.databinding.ActivityMainBinding
import com.chaitanya.foodorder.viewModel.OrderViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> navController.navigate(R.id.mealsFragment)
                R.id.cart -> navController.navigate(R.id.cartFragment)
                R.id.profile -> navController.navigate(R.id.profileFragment)
                R.id.todayMenu -> navController.navigate(R.id.todayMenuFragment)
            }
            true
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.checkoutFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
            when(destination.id){
                R.id.mealsFragment -> binding.bottomNavigation.menu.findItem(R.id.home)?.isChecked = true
                R.id.todayMenuFragment -> binding.bottomNavigation.menu.findItem(R.id.todayMenu)?.isChecked = true
                R.id.cartFragment -> binding.bottomNavigation.menu.findItem(R.id.cart)?.isChecked = true
                R.id.profileFragment -> binding.bottomNavigation.menu.findItem(R.id.profile)?.isChecked = true
            }
        }
        orderViewModel.cartItemList.observe(this) { cartItems ->
            if (cartItems.size != 0) {
                binding.bottomNavigation.getOrCreateBadge(R.id.cart).apply {
                    number = cartItems.size
                    isVisible = true
                }
            } else {
                binding.bottomNavigation.removeBadge(R.id.cart)
            }
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}