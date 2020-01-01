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
import id.shobrun.footballleague.ui.leagues.team.favorite.FavoriteTeamsFragment

private val TAB_TITLES_FAVORITE = arrayOf(
    R.string.tab_previous,
    R.string.tab_next,
    R.string.tab_team
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
        return when(position){
            0 -> {
                val prevFrag = FavoritePreviousEventFragment.newInstance()
                prevFrag.arguments = bundle
                prevFrag    
            }
            1 ->{
                val nextFrag = FavoriteNextEventFragment.newInstance()
                nextFrag.arguments = bundle
                nextFrag
            }
            else -> {
                val favoriteFrag = FavoriteTeamsFragment.newInstance()
                favoriteFrag.arguments = bundle
                favoriteFrag
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES_FAVORITE[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}