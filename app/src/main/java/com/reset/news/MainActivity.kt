package com.reset.news

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reset.news.Fragments.FirstTabFragment
import com.reset.news.Fragments.FifthTabFragment
import com.reset.news.Fragments.SixthTabFragment
import com.reset.news.Fragments.ThirdTabFragment
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

        viewPagerAdapter.addFragment(FirstTabFragment(), getString(R.string.tab_text_1))
        viewPagerAdapter.addFragment(FifthTabFragment(), getString(R.string.tab_text_2))
        viewPagerAdapter.addFragment(ThirdTabFragment(), getString(R.string.tab_text_3))
        viewPagerAdapter.addFragment(FifthTabFragment(), getString(R.string.tab_text_4))
        viewPagerAdapter.addFragment(FifthTabFragment(), getString(R.string.tab_text_5))
        viewPagerAdapter.addFragment(SixthTabFragment(), getString(R.string.tab_text_6))

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

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