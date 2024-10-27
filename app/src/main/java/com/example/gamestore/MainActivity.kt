package com.example.gamestore


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.gamestore.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_category -> openFragment(Categorias())
                R.id.bottom_cart -> openFragment(Carrito())
                R.id.bottom_more -> openFragment(Mas())
            }
            true
        }

        fragmentManager = supportFragmentManager
        if (savedInstanceState == null) {
            openFragment(HomeFragment()) // Solo abrir si no hay estado guardado
        }

        binding.fab.setOnClickListener {
            Toast.makeText(this, "FAB Clicked", Toast.LENGTH_SHORT).show()
        }

        switchTheme()
    }

    private fun closeSesion() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun switchTheme() {
        val navigationView: NavigationView = findViewById(R.id.navigation_drawer)
        val menuItem = navigationView.menu.findItem(R.id.nav_switch)
        val switch = menuItem.actionView?.findViewById<SwitchMaterial>(R.id.my_switch)

        switch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }

    }


    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_prime -> openFragment(HomeFragment())
            R.id.nav_close -> closeSesion()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() // Llama a onBackPressed sin cambios adicionales
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null) // Agrega el fragmento a la pila de retroceso
        fragmentTransaction.commit()
    }
}







































//class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var fragmentManager: FragmentManager
//    private lateinit var binding:ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//
//        setSupportActionBar(binding.toolbar)
//        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.nav_open,R.string.nav_close)
//        binding.drawerLayout.addDrawerListener(toogle)
//        toogle.syncState()
//
//        binding.navigationDrawer.setNavigationItemSelectedListener(this)
//
//        binding.bottomNavigation.background = null
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.bottom_home -> openFragment(HomeFragment())
//                R.id.bottom_category -> openFragment(Categorias())
//                R.id.bottom_cart -> openFragment(Carrito())
//                R.id.bottom_more -> openFragment(Mas())
//            }
//            true
//        }
//
//        fragmentManager = supportFragmentManager
//        openFragment(HomeFragment())
//
//        binding.fab.setOnClickListener {
//            Toast.makeText(this,"",Toast.LENGTH_SHORT).show()
//        }
//
//        switchTheme()
//    }
//
//    private fun closeSesion(){
//        val intent = Intent(this, Login::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun switchTheme(){
//        val navigationView: NavigationView = findViewById(R.id.navigation_drawer)
//        val menuItem = navigationView.menu.findItem(R.id.nav_switch)
//        val switch = menuItem.actionView?.findViewById<SwitchMaterial>(R.id.my_switch)
//
//        switch?.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                enableDarkMode()
//            } else {
//                disableDarkMode()
//            }
//        }
//    }
//
//    private fun enableDarkMode(){
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        delegate.applyDayNight()
//    }
//
//    private fun disableDarkMode(){
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//        delegate.applyDayNight()
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.nav_prime -> openFragment(HomeFragment())
//            R.id.nav_close -> closeSesion()
//        }
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//
//    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressedDispatcher.onBackPressed()
//        }
//    }
//
//
//    private fun openFragment(fragment: Fragment){
//        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container,fragment)
//        fragmentTransaction.commit()
//    }
//
//}