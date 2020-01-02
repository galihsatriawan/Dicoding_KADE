package id.shobrun.footballleague.ui.search

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelActivity
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.testing.OpenForTesting
import id.shobrun.footballleague.ui.adapters.SearchPagerAdapter
import kotlinx.android.synthetic.main.activity_event_detail.*

@OpenForTesting
class SearchActivity : ViewModelActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        simpleToolbarWithHome(toolbar, "Search")

        val sectionsPagerAdapter =
            SearchPagerAdapter(
                applicationContext,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
