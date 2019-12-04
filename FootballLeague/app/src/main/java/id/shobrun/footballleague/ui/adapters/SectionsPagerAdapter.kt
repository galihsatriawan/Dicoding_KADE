package id.shobrun.footballleague.ui.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.events.next.NextEventFragment
import id.shobrun.footballleague.ui.events.previous.PreviousEventFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, val league: League?) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val bundle  = Bundle()
        bundle.putParcelable(PreviousEventFragment.EXTRA_EVENT,(league))
        return if(position == 0 ){
            val prevFrag = PreviousEventFragment.newInstance()
            prevFrag.arguments = bundle
            prevFrag
        } else{
            val nextFrag = NextEventFragment.newInstance()
            nextFrag.arguments = bundle
            nextFrag
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