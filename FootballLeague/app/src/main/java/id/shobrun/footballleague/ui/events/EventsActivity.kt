package id.shobrun.footballleague.ui.events

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import id.shobrun.footballleague.R
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.SectionsPagerAdapter
import id.shobrun.footballleague.ui.events.search.SearchEventsActivity
import kotlinx.android.synthetic.main.activity_events.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

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
        simpleToolbarWithHome(toolbar,"${league?.name ?: "Match"}")

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search ->{
                val intSearch = intentFor<SearchEventsActivity>()
                startActivity(intSearch)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
