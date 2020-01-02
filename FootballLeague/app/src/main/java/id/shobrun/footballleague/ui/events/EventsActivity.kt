package id.shobrun.footballleague.ui.events

import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.shobrun.footballleague.R
import id.shobrun.footballleague.extensions.simpleToolbarWithHome
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_events.*
import org.jetbrains.anko.intentFor

class EventsActivity : AppCompatActivity() {
    var league: League? = null

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
        const val SAVED_STATE_CONTAINER_KEY = "ContainerKey"
        const val SAVED_STATE_CURRENT_TAB_KEY = "CurrentTabKey"
    }

    private var savedStateSparseArray = SparseArray<Fragment.SavedState>()
    private var currentSelectItemId = R.id.navigation_match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        if (savedInstanceState != null) {
            savedStateSparseArray =
                savedInstanceState.getSparseParcelableArray(SAVED_STATE_CONTAINER_KEY)!!
            currentSelectItemId = savedInstanceState.getInt(SAVED_STATE_CURRENT_TAB_KEY)
        }
        if (intent.getParcelableExtra<League>(EXTRA_LEAGUE) != null) {
            league = intent.getParcelableExtra<League>(EXTRA_LEAGUE)
        }

        val bundle = Bundle()
        bundle.putParcelable(EXTRA_LEAGUE, league)

        bottom_navigation.setOnNavigationItemSelectedListener {
            var fragment = Fragment()

            when (it.itemId) {
                R.id.navigation_match -> {
                    fragment = EventsFragment.newInstance()
                    fragment.arguments = bundle
                    swapFragments(R.id.navigation_match, fragment)
                }
                R.id.navigation_favorite -> {
                    fragment = FavoriteFragment.newInstance()
                    fragment.arguments = bundle
                    swapFragments(R.id.navigation_favorite, fragment)
                }
            }



            true
        }
        bottom_navigation.selectedItemId = currentSelectItemId
        simpleToolbarWithHome(toolbar, "${league?.name ?: "Match"}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSparseParcelableArray(SAVED_STATE_CONTAINER_KEY, savedStateSparseArray)
        outState.putInt(SAVED_STATE_CURRENT_TAB_KEY, currentSelectItemId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 0) {
                        popBackStack()
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }

    private fun swapFragments(@IdRes actionId: Int, fragment: Fragment) {
        if (supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
            savedFragmentState(actionId)
            createFragment(fragment, actionId)
        }
    }

    private fun createFragment(fragment: Fragment, actionId: Int) {
        fragment.setInitialSavedState(savedStateSparseArray[actionId])
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun savedFragmentState(actionId: Int) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_container)
        if (currentFragment != null) {
            savedStateSparseArray.put(
                currentSelectItemId,
                supportFragmentManager.saveFragmentInstanceState(currentFragment)
            )
        }
        currentSelectItemId = actionId
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                val intSearch = intentFor<SearchActivity>()
                startActivity(intSearch)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
