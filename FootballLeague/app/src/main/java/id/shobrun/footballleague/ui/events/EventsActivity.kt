package id.shobrun.footballleague.ui.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.SectionsPagerAdapter

class EventsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        var league : League? = null
        if(intent.getParcelableExtra<League>(EXTRA_LEAGUE) != null){
            league = intent.getParcelableExtra<League>(EXTRA_LEAGUE)
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
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
