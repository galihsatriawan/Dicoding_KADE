package id.shobrun.footballleague.ui.leagues.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.shobrun.footballleague.R
import id.shobrun.footballleague.compose.ViewModelFragment
import id.shobrun.footballleague.databinding.FragmentStandingBinding
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.ui.adapters.RecyclerTeamRecordsAdapter
import id.shobrun.footballleague.ui.events.EventsActivity.Companion.EXTRA_LEAGUE
import timber.log.Timber

class StandingFragment : ViewModelFragment() {

    companion object {
        fun newInstance() = StandingFragment()
        val TAG = this.javaClass.name
    }

    var league: League? = null
    private val viewModel by viewModel<StandingViewModel>()
    private lateinit var binding: FragmentStandingBinding
    private lateinit var teamRecordsAdapter: RecyclerTeamRecordsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_standing, container)
        with(binding) {
            lifecycleOwner = this@StandingFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        if (requireArguments().getParcelable<League>(EXTRA_LEAGUE) != null) {
            league = requireArguments().getParcelable(EXTRA_LEAGUE)
            Timber.d("$TAG ${league?.idLeague}")
        }
        viewModel.postLeagueId(league?.idLeague ?: -1)
    }

    private fun initRecycler() {
        teamRecordsAdapter = RecyclerTeamRecordsAdapter(ArrayList())
        binding.rvTeamRecords.adapter = teamRecordsAdapter
    }
}
