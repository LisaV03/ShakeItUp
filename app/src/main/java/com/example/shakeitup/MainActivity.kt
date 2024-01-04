package com.example.shakeitup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.shakeitup.ui.categories.CategoriesFragment
import com.example.shakeitup.ui.ingredients.IngredientsFragment
import com.example.shakeitup.ui.random.RandomFragment
import com.example.shakeitup.ui.search.SearchFragment
import com.example.shakeitup.ui.shoppingList.ShoppingListFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var tabLayout: TabLayout
    var enableTabListener = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.getTabAt(2)?.select()
    }

    //Deal with the changes of tab when back is pressed
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish()
        } else {
            super.onBackPressed()
            changeSelectedTab()
        }
    }

    //Change the tab without calling onTabSelected or onTabReselected
    //Avoid to add a new instance in the backstack
    fun selectTab(position: Int) {
        enableTabListener = false
        tabLayout.getTabAt(position)?.select()
        enableTabListener = true
    }


    //Select the corresponding tab to the last backstack entry
    fun changeSelectedTab() {
        val fragmentManager = supportFragmentManager
        val size = fragmentManager.getBackStackEntryCount()
        var entry = fragmentManager.getBackStackEntryAt(size - 1)
        when (entry.name) {
            "SearchFragment" -> selectTab(0)
            "CategoriesFragment" -> selectTab(1)
            "RandomFragment" -> selectTab(2)
            "IngredientsFragment" -> selectTab(3)
            "ShoppingListFragment" -> selectTab(4)
        }
    }

    //Display a fragment and add it in the backstackœ
    fun displayFragment(fragment: Fragment, name: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(name)
            .commit()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (enableTabListener) {
            tab?.let {
                when (tab.position) {
                    0 -> displayFragment(SearchFragment.newInstance(), "SearchFragment")
                    1 -> displayFragment(CategoriesFragment.newInstance(), "CategoriesFragment")
                    2 -> displayFragment(RandomFragment.newInstance(), "RandomFragment")
                    3 -> displayFragment(IngredientsFragment.newInstance(), "IngredientsFragment")
                    4 -> displayFragment(ShoppingListFragment.newInstance(), "ShoppingListFragment")
                }
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        if (enableTabListener) {
            tab?.let {
                when (tab.position) {
                    0 -> displayFragment(SearchFragment.newInstance(), "SearchFragment")
                    1 -> displayFragment(CategoriesFragment.newInstance(), "CategoriesFragment")
                    2 -> displayFragment(RandomFragment.newInstance(), "RandomFragment")
                    3 -> displayFragment(IngredientsFragment.newInstance(), "IngredientsFragment")
                    4 -> displayFragment(ShoppingListFragment.newInstance(), "ShoppingListFragment")
                }
            }
        }

    }
}