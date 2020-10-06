package com.example.calcaruzhan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private var fragmentSimple: CalcFragment? = null
    private val SIMPLE_FRAGMENT_TAG = "myfragmenttag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) { // saved instance state, fragment may exist
            // look up the instance that already exists by tag
            fragmentSimple =
                supportFragmentManager.findFragmentByTag(SIMPLE_FRAGMENT_TAG) as CalcFragment?
        } else if (fragmentSimple == null) {
            // only create fragment if they haven't been instantiated already
            fragmentSimple = CalcFragment()
        }
        fragmentManager = supportFragmentManager
        if (!fragmentSimple?.isInLayout!!) {
            fragmentManager.beginTransaction()
                .replace(R.id.main_nav_fragment, fragmentSimple!!, SIMPLE_FRAGMENT_TAG)
                .commit()
        }


    }
}