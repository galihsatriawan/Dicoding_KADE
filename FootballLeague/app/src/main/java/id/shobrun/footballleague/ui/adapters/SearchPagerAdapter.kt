package id.shobrun.footballleague.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.shobrun.footballleague.R
import id.shobrun.footballleague.ui.search.event.SearchEventsFragment
import id.shobrun.footballleague.ui.search.team.SearchTeamsFragment


private val TAB_TITLES = arrayOf(
    R.string.tab_event,
    R.string.tab_team

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SearchPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            SearchEventsFragment.newInstance()
        } else {
            SearchTeamsFragment.newInstance()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}