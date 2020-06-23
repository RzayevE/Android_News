package com.reset.news

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reset.news.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = viewPagerAdapter(supportFragmentManager)
        val region = intent.getStringExtra("region")

        Toast.makeText(this, region, Toast.LENGTH_LONG).show()

        viewPagerAdapter.addFragment(FirstTabFragment(), getString(R.string.tab_text_1))
        viewPagerAdapter.addFragment(SecondTabFragment(), getString(R.string.tab_text_2))
        viewPagerAdapter.addFragment(ThirdTabFragment(), getString(R.string.tab_text_3))
        viewPagerAdapter.addFragment(FourthTabFragment(), getString(R.string.tab_text_4))
        viewPagerAdapter.addFragment(FifthTabFragment(), getString(R.string.tab_text_5))
        viewPagerAdapter.addFragment(SixthTabFragment(), getString(R.string.tab_text_6))

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.region1 -> {
                val intent = Intent(this, MainActivity::class.java)
                val region = "ru"
                intent.putExtra("region", region)
                finish()
                startActivity(intent)
//                Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
                true
            }
            R.id.region2 -> {
                val intent = Intent(this, MainActivity::class.java)
                val region = "us"
                intent.putExtra("region", region)
                finish()
                startActivity(intent)
//                Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
                true
            }
            R.id.region3 -> {
                val intent = Intent(this, MainActivity::class.java)
                val region = "tr"
                intent.putExtra("region", region)
                finish()
                startActivity(intent)
//                Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    internal class viewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }


        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

    }
}