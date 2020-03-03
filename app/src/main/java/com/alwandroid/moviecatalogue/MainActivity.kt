package com.alwandroid.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alwandroid.moviecatalogue.ui.movie.MovieFragment
import com.alwandroid.moviecatalogue.ui.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var fragment: Fragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movie -> {
                    fragment = MovieFragment()
                }
                R.id.navigation_tvshow -> {
                    fragment = TvShowFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            supportActionBar?.title = resources.getString(R.string.app_name)
            true
        }

        if (savedInstanceState == null){
            navView.selectedItemId = R.id.navigation_movie
            supportActionBar?.title = resources.getString(R.string.app_name)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language_menu) startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        return super.onOptionsItemSelected(item)
    }
}
