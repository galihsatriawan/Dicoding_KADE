package id.shobrun.footballleague.ui.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import id.shobrun.footballleague.ui.events.favorite.next.FavoriteNextEventFragment
import id.shobrun.footballleague.ui.events.favorite.previous.FavoritePreviousEventFragment

private val TAB_TITLES_FAVORITE = arrayOf(
    R.string.tab_previous,
    R.string.tab_next
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class FavoriteSectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    val league: League?
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_LEAGUE, (league))
        return if (position == 0) {
            val prevFrag = FavoritePreviousEventFragment.newInstance()
            prevFrag.arguments = bundle
            prevFrag
        } else {
            val nextFrag = FavoriteNextEventFragment.newInstance()
            nextFrag.arguments = bundle
            nextFrag
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES_FAVORITE[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}