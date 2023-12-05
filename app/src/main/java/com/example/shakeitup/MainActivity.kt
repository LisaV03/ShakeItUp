package com.example.shakeitup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.ui.categories.CategoriesFragment
import com.example.shakeitup.ui.ingredients.IngredientsFragment
import com.example.shakeitup.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.addOnTabSelectedListener(this)

        displaySearchFragment()
        //display first fragment
        //val categoriesFragment = CategoriesFragment()
        //supportFragmentManager.beginTransaction().replace(R.id.fragment_category, CategoriesFragment.newInstance()).commit()
    }

    fun displayCategoriesFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, CategoriesFragment.newInstance())
            .addToBackStack("")
            .commit()
    }

    fun displaySearchFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, SearchFragment.newInstance())
            .addToBackStack("")
            .commit()
    }

    fun displayIngredientsFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, IngredientsFragment.newInstance())
            .addToBackStack("")
            .commit()

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        Log.i("Tab", "onTabSelected");
        tab?.let {
            when (tab.position) { // comme un switch case
                0 -> displaySearchFragment()
                1 -> displayCategoriesFragment()
                2 -> displayIngredientsFragment()
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }




}