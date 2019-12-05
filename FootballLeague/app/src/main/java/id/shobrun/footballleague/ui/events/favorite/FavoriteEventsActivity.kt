package id.shobrun.footballleague.ui.events.favorite

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import id.shobrun.footballleague.R
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.FavoriteSectionsPagerAdapter
import id.shobrun.footballleague.ui.adapters.SectionsPagerAdapter
import id.shobrun.footballleague.ui.events.EventsActivity
import kotlinx.android.synthetic.main.activity_events.*


class FavoriteEventsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_events)
        var league : League? = null
        if(intent.getParcelableExtra<League>(EventsActivity.EXTRA_LEAGUE) != null){
            league = intent.getParcelableExtra<League>(EventsActivity.EXTRA_LEAGUE)
        }
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                applicationContext,
                supportFragmentManager,
                league
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        simpleToolbarWithHome(toolbar,"Favorite ${league?.name ?: "Favorite Match"}")
    }
}