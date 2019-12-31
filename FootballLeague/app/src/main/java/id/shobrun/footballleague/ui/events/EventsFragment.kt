package id.shobrun.footballleague.ui.events


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.SectionsPagerAdapter
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE

/**
 * A simple [Fragment] subclass.
 */
class EventsFragment : Fragment() {
    companion object {
        fun newInstance() = EventsFragment()
    }

    var league: League? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        if (requireArguments().getParcelable<League>(EXTRA_LEAGUE) != null) {
            league = requireArguments().getParcelable(EXTRA_LEAGUE)
        }

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                requireContext(),
                childFragmentManager,
                league
            )
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        return view
    }


}
