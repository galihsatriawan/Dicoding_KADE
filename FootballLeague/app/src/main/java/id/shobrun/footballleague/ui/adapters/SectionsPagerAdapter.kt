package id.shobrun.footballleague.ui.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.events.next.NextEventFragment
import id.shobrun.footballleague.ui.events.previous.PreviousEventFragment
import id.shobrun.footballleague.ui.leagues.standing.StandingFragment
import id.shobrun.footballleague.ui.leagues.team.TeamsFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_previous,
    R.string.tab_next,
    R.string.tab_standing,
    R.string.tab_team
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, val league: League?) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_LEAGUE, (league))
        return when (position) {
            0 -> {
                val prevFrag = PreviousEventFragment.newInstance()
                prevFrag.arguments = bundle
                prevFrag
            }
            1 -> {
                val nextFrag = NextEventFragment.newInstance()
                nextFrag.arguments = bundle
                nextFrag
            }
            2 -> {
                val standing = StandingFragment.newInstance()
                standing.arguments = bundle
                standing
            }
            else -> {
                val teams = TeamsFragment.newInstance()
                teams.arguments = bundle
                teams
            }
        }


    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }
}