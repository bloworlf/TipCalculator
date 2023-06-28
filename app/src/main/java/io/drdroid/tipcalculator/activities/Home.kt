package io.drdroid.tipcalculator.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import io.drdroid.tipcalculator.R
import io.drdroid.tipcalculator.base.BaseActivity
import io.drdroid.tipcalculator.databinding.ActivityHomeBinding

class Home : BaseActivity() {

    lateinit var bind: ActivityHomeBinding
    lateinit var controller: NavController
    lateinit var navView: BottomNavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        toolbar = bind.toolbar
        setSupportActionBar(toolbar)

        navView = bind.btmNavView
        controller = findNavController(R.id.fragment)
        val navConfig = AppBarConfiguration(
            setOf(R.id.dashboard_menu, R.id.categories_menu, R.id.search_menu)
        )

        setupActionBarWithNavController(controller, navConfig)
        navView.setupWithNavController(controller)
    }
}